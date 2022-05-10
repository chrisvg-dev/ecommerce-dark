package com.acr.apps.ecommerce.controller;

import com.acr.apps.ecommerce.dto.CommentDto;
import com.acr.apps.ecommerce.dto.ResponseDto;
import com.acr.apps.ecommerce.entity.Comment;
import com.acr.apps.ecommerce.enums.StatusEnum;
import com.acr.apps.ecommerce.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<List<Comment>> getAll(){
        List<Comment> comments = this.commentService.findByStatus( StatusEnum.CREATED);

        return ResponseEntity.ok(comments);
    }

    @PostMapping
    public ResponseEntity<ResponseDto> save(@RequestBody CommentDto commentDto){
        ResponseDto response = new ResponseDto(null, "Registro éxitoso...");
        this.commentService.save(commentDto);

        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable Long id){
        if (id < 1) {
            return new ResponseEntity(
                    new ResponseDto(401, "Debe ingresar un id válido"),
                    HttpStatus.NOT_FOUND
            );
        }

        Optional<Comment> opt = this.commentService.delete(id);
        if (opt == null){
            return new ResponseEntity(
                    new ResponseDto(null, "Error al intentar eliminar"),
                    HttpStatus.BAD_REQUEST
            );
        }

        return new ResponseEntity(
                new ResponseDto(null, "Registro eliminado"),
                HttpStatus.OK
        );
    }

    @PutMapping
    public ResponseEntity<ResponseDto> update(@RequestBody CommentDto commentDto){
        if (commentDto.getId() < 1 || commentDto.getId() == null){
            return new ResponseEntity(
                    new ResponseDto(401, "Debe ingresar un id válido"),
                    HttpStatus.NOT_FOUND
            );
        }

        ResponseDto response = null;
        Comment updated = this.commentService.update(commentDto).get();
        if (updated == null){
            response = new ResponseDto(null, "No se encontró el registro... ");
        } else {
            response = new ResponseDto(null, "Registro actualizado");
        }

        return new ResponseEntity( response, HttpStatus.OK);
    }
}
