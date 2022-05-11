package com.acr.apps.ecommerce.controller;

import com.acr.apps.ecommerce.dto.ProductPostDto;
import com.acr.apps.ecommerce.dto.ResponseDto;
import com.acr.apps.ecommerce.entity.ProductPost;
import com.acr.apps.ecommerce.enums.StatusEnum;
import com.acr.apps.ecommerce.service.ProductPost.ProductPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/productsPost")
public class ProductPostController {
    @Autowired
    private ProductPostService productPostS;

    @GetMapping
    public ResponseEntity<List<ProductPost>> getAll(){
        List<ProductPost> productPosts = this.productPostS.findByStatus(StatusEnum.CREATED);

        return ResponseEntity.ok((productPosts));
    }

    @PostMapping
    public  ResponseEntity<ResponseDto> save(@RequestBody ProductPostDto productPostDto){
        ResponseDto responseDto = new ResponseDto(null, "Registro exitoso...");
        this.productPostS.save(productPostDto);
        return new ResponseEntity(responseDto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ResponseDto> update(@RequestBody ProductPostDto productPostDto){
        if(productPostDto.getId() < 1 || productPostDto.getId() == null){
            return new ResponseEntity(
                    new ResponseDto(401, "Debe ingresar un id valido"),
                    HttpStatus.NOT_FOUND
            );
        }

        ResponseDto responseDto = null;
        ProductPost updateProductP = this.productPostS.update(productPostDto).get();
        if(updateProductP == null){
            responseDto = new ResponseDto(null, "No se encontró el registro");
        } else {
            responseDto =  new ResponseDto(null, "Registro actualizado");
        }
        return  new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable Long id){
        if (id < 1){
            return new ResponseEntity(
                    new ResponseDto(401, "Debe ingresar un id válido"),
                    HttpStatus.NOT_FOUND
            );
        }
        Optional<ProductPost> optional = this.productPostS.delete(id);
        if (optional == null){
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
}
