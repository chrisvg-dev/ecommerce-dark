package com.acr.apps.ecommerce.service;

import com.acr.apps.ecommerce.dto.CommentDto;
import com.acr.apps.ecommerce.entity.Comment;
import com.acr.apps.ecommerce.enums.StatusEnum;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<Comment> save(CommentDto commentDto);
    Optional<Comment> update(CommentDto commentDto);
    List<Comment> getAll();
    Optional<Comment> delete(Long id);
    List<Comment> findByStatus(StatusEnum status);
}
