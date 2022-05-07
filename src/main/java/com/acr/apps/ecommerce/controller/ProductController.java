package com.acr.apps.ecommerce.controller;

import com.acr.apps.ecommerce.dto.ProductDto;
import com.acr.apps.ecommerce.dto.ResponseDto;
import com.acr.apps.ecommerce.entity.Product;
import com.acr.apps.ecommerce.enums.StatusEnum;
import com.acr.apps.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        List<Product> products = this.productService.findByStatus( StatusEnum.CREATED );
        return ResponseEntity.ok( products );
    }

    @PostMapping
    public ResponseEntity<ResponseDto> save(@RequestBody ProductDto product) {
        ResponseDto response = new ResponseDto(null, "Registro exitoso...");
        this.productService.save(product);
        return new ResponseEntity( response,  HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable Long id) {
        if ( id < 1) {
            return new ResponseEntity(
                    new ResponseDto(401, "Debe ingresar un id válido"),
                    HttpStatus.NOT_FOUND
            );
        }
        Optional<Product> opt = this.productService.delete(id);
        if (opt == null) {
            return new ResponseEntity(
                    new ResponseDto(null, "ERROR AL INTENTAR ELIMINAR"),
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity(
                new ResponseDto(null, "Registro eliminado"),
                HttpStatus.OK
        );
    }

    @PutMapping
    public ResponseEntity<ResponseDto> update(@RequestBody ProductDto product) {
        if ( product.getId() < 1 || product.getId() == null) {
            return new ResponseEntity(
                    new ResponseDto(401, "Debe ingresar un id válido"),
                    HttpStatus.NOT_FOUND
            );
        }

        ResponseDto response = null;
        Product updated = this.productService.update(product).get();
        if ( updated == null ) {
            response = new ResponseDto(null, "No se encontró el registro...");
        } else {
            response = new ResponseDto(null, "Registro actualizado");
        }
        return new ResponseEntity( response,  HttpStatus.OK);
    }
}