package com.bootcamp.shoppingcart.services.interfaces;

import com.bootcamp.shoppingcart.domain.entities.CategoryEntity;

import java.util.List;

public interface CategoryService {

    List<CategoryEntity> getAllCategories();

    CategoryEntity getCategoryById(Integer id);

    CategoryEntity updateCategory (CategoryEntity categoryEntity);

    CategoryEntity createCategory (CategoryEntity categoryEntity);

    void deleteCategory (Integer id);

}
