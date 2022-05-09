package com.acr.apps.ecommerce.controller;

import com.acr.apps.ecommerce.dto.CategoryDto;
import com.acr.apps.ecommerce.dto.ResponseDto;
import com.acr.apps.ecommerce.entity.Category;
import com.acr.apps.ecommerce.enums.StatusEnum;
import com.acr.apps.ecommerce.service.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAll(){
        List<Category> categoryList =  this.categoryService.findByStatus( StatusEnum.CREATED );
        return  ResponseEntity.ok(categoryList);
    }

    @PostMapping
    public ResponseEntity<ResponseDto> save(@RequestBody CategoryDto categoryDto){
        ResponseDto response =  new ResponseDto(null, "Registro exitoso...");
        this.categoryService.save(categoryDto);
        return  new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ResponseDto> update (@RequestBody CategoryDto categoryDto){
        if (categoryDto.getId() < 1 || categoryDto.getId() == null){
            return new ResponseEntity(
                    new ResponseDto(401, "Debe ingresar un id valido"),
                    HttpStatus.NOT_FOUND
            );
        }

        ResponseDto responseDto = null;
        Category categoryUpdate = this.categoryService.update(categoryDto).get();
        if ( categoryUpdate == null){
            responseDto =  new ResponseDto(null, "No se econtró el registro");
        } else {
            responseDto =  new ResponseDto(null, "Registro actualizado");
        }
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete (@PathVariable Long id){
        if (id < 1) {
            return new ResponseEntity(
                    new ResponseDto(401, "Debe ingresar un id valido"),
                    HttpStatus.NOT_FOUND
            );
        }
        Optional<Category> optional =  this.categoryService.delete(id);
        if(optional == null){
            return new ResponseEntity(
                    new ResponseDto(null, "Error AL INTENTAR ELIMINAR"),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(
                new ResponseDto(null, "Registro eliminado"),
                HttpStatus.OK
        );
    }

}
