package com.acr.apps.ecommerce.repository;


import com.acr.apps.ecommerce.entity.Comment;
import com.acr.apps.ecommerce.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByStatus(StatusEnum status);
    @Query("SELECT c FROM Comment c WHERE c.status = :status")
    List<Comment> findAll(@Param("status") StatusEnum ...status);
    Optional<Comment> findById(Long id);
}
