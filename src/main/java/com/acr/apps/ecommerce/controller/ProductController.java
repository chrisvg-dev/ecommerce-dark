package com.acr.apps.ecommerce.controller;

import com.acr.apps.ecommerce.dto.ProductDto;
import com.acr.apps.ecommerce.dto.ResponseDto;
import com.acr.apps.ecommerce.entity.Product;
import com.acr.apps.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        List<Product> products = this.productService.getAll();
        return ResponseEntity.ok( products );
    }

    @PostMapping
    public ResponseEntity<ResponseDto> save(@RequestBody ProductDto producto) {
        ResponseDto response = new ResponseDto(null, "Registro exitoso...");
        System.out.println(producto);
        return new ResponseEntity( response,  HttpStatus.CREATED);
    }
}