package com.acr.apps.ecommerce.service.Category;

import com.acr.apps.ecommerce.dto.CategoryDto;
import com.acr.apps.ecommerce.entity.Category;
import com.acr.apps.ecommerce.enums.StatusEnum;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<Category> save (CategoryDto categoryDto);
    Optional<Category> update(CategoryDto categoryDto);
    Optional<Category> delete(Long id);
    List<Category> findByStatus(StatusEnum status);
    List<Category> getAll();
}
