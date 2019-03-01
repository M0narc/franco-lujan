package com.bootcamp.shoppingcart.controllers;

import com.bootcamp.shoppingcart.domain.entities.CategoryEntity;
import com.bootcamp.shoppingcart.services.implementations.DefaultCategoryService;
import com.bootcamp.shoppingcart.services.implementations.DefaultLoginService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {


    @Mock
    private DefaultCategoryService defaultCategoryService;

    @Mock
    private DefaultLoginService defaultLoginService;

    @InjectMocks
    private CategoryController categoryController;

    private CategoryEntity categoryEntity;
    private List<CategoryEntity> categoryEntityList;

    @Before
    public void setup(){
        categoryEntity = new CategoryEntity(1,"category","description", Collections.emptyList());
        categoryEntityList = Arrays.asList(categoryEntity,null,categoryEntity,null);
        Mockito.when(defaultLoginService.validate(any(),any())).thenReturn(true);
    }

    @Test
    public void ShouldReturnOkWithCategoryEntityList(){
        Mockito.when(defaultCategoryService.getAllCategories()).thenReturn(categoryEntityList);

        List<CategoryEntity> expected = categoryController.getAllCategories("nn","qwerty");

        assertEquals("Should return all categories",expected,categoryEntityList);
    }

    @Test
    public void ShouldReturnOkWithCategoryListAndFindById(){
        Mockito.when(defaultCategoryService.getCategoryById(any())).thenReturn(categoryEntity);

        CategoryEntity expected = categoryController.getCategoryById(1,"nn","qwerty");

        assertEquals("Expected should return id",expected,categoryEntity);

    }

    @Test
    public void ShouldReturnOkwithNewCategory(){
        Mockito.when(defaultCategoryService.createCategory(any())).thenReturn(categoryEntity);

        CategoryEntity expected = categoryController.createCategory(new CategoryEntity(),"nn","qwerty");

        assertEquals("Expected should return new category", expected, categoryEntity);
    }

    @Test
    public void ShouldReturnOkWithUpdatedCategory(){
        Mockito.when(defaultCategoryService.updateCategory(any())).thenReturn(categoryEntity);

        CategoryEntity expected = categoryController.updateCategory(new CategoryEntity(),"nn","qwerty");

        assertEquals("Expected should return updated category", expected, categoryEntity);
    }

    @Test
    public void ShouldReturnNoContentAndDeleteCategory(){

        categoryController.deleteCategoryById(1,"nn","qwerty");

        Mockito.verify(defaultCategoryService,Mockito.times(1)).deleteCategory(any());
        Mockito.verify(defaultLoginService,Mockito.times(1)).validate(any(),any());
    }
}