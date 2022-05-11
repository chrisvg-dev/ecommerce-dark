package com.acr.apps.ecommerce.service.ProductPost;

import com.acr.apps.ecommerce.dto.ProductPostDto;
import com.acr.apps.ecommerce.entity.ProductPost;
import com.acr.apps.ecommerce.enums.StatusEnum;

import java.util.List;
import java.util.Optional;

public interface ProductPostService {
    Optional<ProductPost> save(ProductPostDto productPostDto);
    Optional<ProductPost> update(ProductPostDto productPostDto);
    Optional<ProductPost> delete(Long id);
    List<ProductPost> findByStatus(StatusEnum status);
    List<ProductPost> getAll ();
}
