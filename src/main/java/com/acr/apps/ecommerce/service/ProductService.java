package com.acr.apps.ecommerce.service;

import com.acr.apps.ecommerce.dto.ProductDto;
import com.acr.apps.ecommerce.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> save(ProductDto productDto);
    Optional<Product> update(ProductDto productDto);
    List<Product> getAll();
    Optional<Product> delete(Long id);
}