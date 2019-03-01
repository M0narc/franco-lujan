package com.bootcamp.shoppingcart.services.implementations.services.implementations;

import com.bootcamp.shoppingcart.domain.entities.CategoryEntity;
import com.bootcamp.shoppingcart.domain.repositories.CategoryRepository;
import com.bootcamp.shoppingcart.exceptions.NotFoundException;
import com.bootcamp.shoppingcart.services.implementations.DefaultCategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

/*
verificar el tiempo de compilacion
y te proteje de regrecion
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultCategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private DefaultCategoryService defaultCategoryService;

    private CategoryEntity categoryEntity;
    private List<CategoryEntity> categoryEntityList;

    @Before
    public void setup(){
        categoryEntity =  new CategoryEntity(5,"franco","mi descripcion", null);
        categoryEntityList = Arrays.asList(categoryEntity,null,categoryEntity,null,categoryEntity);
    }

    @Test
    public void testShouldReturnListOfCategories(){
        Mockito.when(categoryRepository.findAll()).thenReturn(categoryEntityList);

        List<CategoryEntity> expected = defaultCategoryService.getAllCategories();

        assertEquals("Expected list should match provided list",expected,categoryEntityList);
    }

    @Test
    public void testShouldReturnACategory(){
        //arrange
        Mockito.when(categoryRepository.findById(any())).thenReturn(Optional.of(categoryEntity));
        //act
        CategoryEntity expected = defaultCategoryService.getCategoryById(4);

        assertEquals("Expected should return match category.",expected,categoryEntity);
    }

    @Test (expected = NotFoundException.class)
    public void testShouldThrowNotFoundException(){
        Mockito.when(categoryRepository.findById(any())).thenReturn(Optional.empty());

        defaultCategoryService.getCategoryById(2);
    }

    @Test
    public void testShouldReturnCreatedEntity(){
        Mockito.when(categoryRepository.save(any())).thenReturn(categoryEntity);

        CategoryEntity expected = defaultCategoryService.createCategory(new CategoryEntity());

        assertEquals("Expected should match new category.",expected,categoryEntity);
    }

    @Test
    public void testShouldReturnUpdatedEntity(){
        Mockito.when(categoryRepository.findById(any())).thenReturn(Optional.of(new CategoryEntity()));
        Mockito.when(categoryRepository.save(any())).thenReturn(categoryEntity);

        CategoryEntity expected = defaultCategoryService.updateCategory(categoryEntity);

        assertEquals("Expected should match update.",expected,categoryEntity);
    }

    @Test (expected = NotFoundException.class)
    public void testShouldThrownNotFoundWhenUpdateEntityDoesNotExist(){
        Mockito.when(categoryRepository.findById(any())).thenReturn(Optional.empty());

        defaultCategoryService.updateCategory(new CategoryEntity());
    }

    @Test
    public void testShouldReturnNothingWhenDeleted(){
        Mockito.when(categoryRepository.findById(any())).thenReturn(Optional.of(new CategoryEntity()));

        defaultCategoryService.deleteCategory(4);

        Mockito.verify(categoryRepository, Mockito.times(1)).findById(any());
        Mockito.verify(categoryRepository, Mockito.times(1)).deleteById(any());
    }

    @Test (expected = NotFoundException.class)
    public void testShouldThrowExceptionWhenDeletedEntityIsNotFound(){
        Mockito.when(categoryRepository.findById(any())).thenReturn(Optional.empty());

        defaultCategoryService.deleteCategory(2);
    }
}