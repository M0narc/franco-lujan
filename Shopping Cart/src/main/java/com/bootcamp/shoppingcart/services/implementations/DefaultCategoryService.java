package com.bootcamp.shoppingcart.services.implementations;

import com.bootcamp.shoppingcart.constants.ExceptionMessages;
import com.bootcamp.shoppingcart.domain.entities.CategoryEntity;
import com.bootcamp.shoppingcart.domain.repositories.CategoryRepository;
import com.bootcamp.shoppingcart.exceptions.NotFoundException;
import com.bootcamp.shoppingcart.services.interfaces.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCategoryService implements CategoryService {


    private CategoryRepository categoryRepository;

    private DefaultCategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryEntity getCategoryById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessages.NOT_FOUND_CATEGORY.getString()));
    }

    @Override   //Create exception if possible
    public CategoryEntity createCategory(CategoryEntity categoryEntity) {
        categoryEntity.setId(null);
        return categoryRepository.save(categoryEntity);
    }

    @Override
    public CategoryEntity updateCategory(CategoryEntity categoryEntity) {
        categoryRepository.findById(categoryEntity.getId()).orElseThrow(() -> new NotFoundException(ExceptionMessages.NOT_FOUND_CATEGORY.getString()));
        return categoryRepository.save(categoryEntity);
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessages.NOT_FOUND_CATEGORY.getString()));
        categoryRepository.deleteById(id);
    }
}
