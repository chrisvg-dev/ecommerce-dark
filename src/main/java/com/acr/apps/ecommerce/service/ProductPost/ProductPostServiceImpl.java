package com.acr.apps.ecommerce.service.ProductPost;

import com.acr.apps.ecommerce.dto.ProductPostDto;
import com.acr.apps.ecommerce.entity.ProductPost;
import com.acr.apps.ecommerce.enums.StatusEnum;
import com.acr.apps.ecommerce.repository.ProductPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductPostServiceImpl implements ProductPostService{

    @Autowired
    private ProductPostRepository productPRepository;

    @Override
    public Optional<ProductPost> save(ProductPostDto productPostDto) {
        try{
            ProductPost newProductPost = ProductPost.builder()
                    .id(0L)
                    .detail(productPostDto.getDetail())
                    .description(productPostDto.getDescription())
                    .createdAt(LocalDateTime.now())
                    .status(StatusEnum.CREATED)
                    .build();

            return Optional.ofNullable(this.productPRepository.save(newProductPost));
        } catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<ProductPost> update(ProductPostDto productPostDto) {
        try{
            ProductPost updateProductPost = this.productPRepository.getById(productPostDto.getId());

            if(updateProductPost == null) return Optional.empty();

            updateProductPost.setDetail(productPostDto.getDetail());
            updateProductPost.setDescription(productPostDto.getDescription());
            updateProductPost.setStatus(StatusEnum.UPDATED);

            return Optional.ofNullable(this.productPRepository.save(updateProductPost));
        } catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<ProductPost> delete(Long id) {
        ProductPost deleteProductP = this.productPRepository.findById(id).orElse(null);

        if(deleteProductP == null) return null;

        deleteProductP.setStatus(StatusEnum.DELETED);
        return Optional.ofNullable(this.productPRepository.save(deleteProductP));
    }

    @Override
    public List<ProductPost> findByStatus(StatusEnum status) {
        return this.productPRepository.findByStatus(status);
    }

    @Override
    public List<ProductPost> getAll() {
        return this.productPRepository.findAll();
    }
}
