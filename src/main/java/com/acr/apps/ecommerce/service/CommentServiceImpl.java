package com.acr.apps.ecommerce.service;

import com.acr.apps.ecommerce.dto.CommentDto;
import com.acr.apps.ecommerce.entity.Comment;
import com.acr.apps.ecommerce.enums.StatusEnum;
import com.acr.apps.ecommerce.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Optional<Comment> save(CommentDto commentDto) {
        try {
            Comment newComment = Comment.builder()
                    .id(0L)
                    .comment( commentDto.getComment() )
                    .createdAt( LocalDateTime.now() )
                    .status( StatusEnum.CREATED )
                    .build();
            return Optional.ofNullable(this.commentRepository.save(newComment));

        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Comment> update(CommentDto commentDto) {
        try {
            Comment toUpdate = this.commentRepository.getById( commentDto.getId() );

            if (toUpdate == null){
                return Optional.empty();
            }

            toUpdate.setComment(commentDto.getComment());
            toUpdate.setStatus(StatusEnum.UPDATED);

            return Optional.ofNullable(this.commentRepository.save(toUpdate));

        } catch(Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Comment> getAll() {
        List<Comment> comments = this.commentRepository.findAll();
        return comments;
    }

    @Override
    public Optional<Comment> delete(Long id) {
        Comment toDelete = this.commentRepository.findById(id).orElse(null);

        if (toDelete == null){
            return null;
        }

        toDelete.setStatus(StatusEnum.DELETED);
        return Optional.ofNullable(this.commentRepository.save(toDelete));
    }

    @Override
    public List<Comment> findByStatus(StatusEnum status) {

        return this.commentRepository.findByStatus(status);
    }
}
