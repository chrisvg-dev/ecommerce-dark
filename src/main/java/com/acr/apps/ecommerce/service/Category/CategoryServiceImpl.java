package com.acr.apps.ecommerce.service.Category;

import com.acr.apps.ecommerce.dto.CategoryDto;
import com.acr.apps.ecommerce.entity.Category;
import com.acr.apps.ecommerce.enums.StatusEnum;
import com.acr.apps.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Optional<Category> save(CategoryDto categoryDto) {
        try{
            Category newCategory = Category.builder()
                    .id(0L)
                    .name(categoryDto.getName())
                    .status(StatusEnum.CREATED)
                    .createdAt(LocalDateTime.now())
                    .build();

            return Optional.ofNullable( this.categoryRepository.save(newCategory) );

        }  catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Category> update(CategoryDto categoryDto) {
        try {
            Category categoryUpdate = this.categoryRepository.getById( categoryDto.getId() );

            if (categoryUpdate == null) return Optional.empty();

            categoryUpdate.setName(categoryDto.getName());
            categoryUpdate.setStatus(StatusEnum.UPDATED);

            return Optional.ofNullable( this.categoryRepository.save(categoryUpdate) );

        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Category> delete(Long id) {
       Category categoryDelete = this.categoryRepository.findById(id).orElse(null);

       if(categoryDelete == null) return  null;

       categoryDelete.setStatus( StatusEnum.DELETED );
       return Optional.ofNullable(this.categoryRepository.save(categoryDelete));
    }

    @Override
    public List<Category> findByStatus(StatusEnum status) {
        return this.categoryRepository.findByStatus(status);
    }

    @Override
    public List<Category> getAll() {
        List<Category> categoryList = this.categoryRepository.findAll();
        return categoryList;
    }
}
