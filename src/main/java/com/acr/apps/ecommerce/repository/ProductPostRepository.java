package com.acr.apps.ecommerce.repository;

import com.acr.apps.ecommerce.entity.ProductPost;
import com.acr.apps.ecommerce.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductPostRepository extends JpaRepository<ProductPost, Long> {
    List<ProductPost> findByStatus(StatusEnum status);

    @Query("SELECT p FROM ProductPost p WHERE p.status = :status")
    List<ProductPost> findAll(@Param("status") StatusEnum ...status);

    Optional<ProductPost> findById(Long id);
}
