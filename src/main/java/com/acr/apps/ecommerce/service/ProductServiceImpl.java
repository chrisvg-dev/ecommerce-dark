package com.acr.apps.ecommerce.service;

import com.acr.apps.ecommerce.dto.ProductDto;
import com.acr.apps.ecommerce.entity.Product;
import com.acr.apps.ecommerce.enums.StatusEnum;
import com.acr.apps.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Optional<Product> save(ProductDto productDto) {
        try {
            Product newProduct = Product.builder()
                    .id(0L)
                    .name( productDto.getName() )
                    .description( productDto.getDescription() )
                    .price( productDto.getPrice() )
                    .availableAmount( productDto.getAvailableAmount() )
                    .stock( productDto.getStock() )
                    .qualification( productDto.getQualification() )
                    .mark( productDto.getMark() )
                    .model( productDto.getModel() )
                    .year( productDto.getYear() )
                    .createdAt( LocalDateTime.now() )
                    .status( StatusEnum.CREATED )
                    .build();

            return Optional.ofNullable( this.productRepository.save(newProduct) );

        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = this.productRepository.findAll();
        return products;
    }

    @Override
    public Optional<Product> delete(Long id) {
        Product toDelete = this.productRepository.findById(id).orElse(null);

        if (toDelete == null || toDelete.getStatus().equals( StatusEnum.DELETED )) {
            return null;
        }

        toDelete.setStatus( StatusEnum.DELETED );
        return Optional.ofNullable( this.productRepository.save( toDelete ) );
    }

    @Override
    public List<Product> findByStatus(StatusEnum status) {
        return this.productRepository.findByStatus(status);
    }

    @Override
    public Optional<Product> update(ProductDto productDto) {
        try {
            Product toUpdate = this.productRepository.getById( productDto.getId() );

            if (toUpdate == null) {
                return Optional.empty();
            }

            toUpdate.setName( productDto.getName() );
            toUpdate.setDescription( productDto.getDescription() );
            toUpdate.setPrice( productDto.getPrice() );
            toUpdate.setMark( productDto.getMark() );
            toUpdate.setModel( productDto.getModel() );
            toUpdate.setYear( productDto.getYear() );
            toUpdate.setStatus( StatusEnum.UPDATED );

            return Optional.ofNullable( this.productRepository.save(toUpdate) );

        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
