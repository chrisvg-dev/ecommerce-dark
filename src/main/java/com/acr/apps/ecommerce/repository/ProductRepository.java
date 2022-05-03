package com.acr.apps.ecommerce.repository;

import com.acr.apps.ecommerce.entity.Product;
import com.acr.apps.ecommerce.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByStatus(StatusEnum status);

    @Query("SELECT p FROM Product p WHERE p.status = :status")
    List<Product> findAll(@Param("status") StatusEnum ...status);
}
