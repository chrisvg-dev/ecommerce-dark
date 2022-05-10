package com.acr.apps.ecommerce.repository;

import com.acr.apps.ecommerce.entity.Category;
import com.acr.apps.ecommerce.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByStatus(StatusEnum status);

    //Buscar categorias que se encuentren en un estado en especifico
    @Query("SELECT c FROM Category c WHERE c.status = :status")
    List<Category> findAll(@Param("status") StatusEnum ...status);

    Optional<Category> findById(Long id);
}
