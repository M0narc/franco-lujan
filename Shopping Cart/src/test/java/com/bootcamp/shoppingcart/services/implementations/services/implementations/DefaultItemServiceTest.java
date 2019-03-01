package com.bootcamp.shoppingcart.services.implementations.services.implementations;

import com.bootcamp.shoppingcart.domain.entities.CategoryEntity;
import com.bootcamp.shoppingcart.domain.entities.ItemEntity;
import com.bootcamp.shoppingcart.domain.repositories.CategoryRepository;
import com.bootcamp.shoppingcart.domain.repositories.ItemRepository;
import com.bootcamp.shoppingcart.exceptions.NotFoundException;
import com.bootcamp.shoppingcart.services.implementations.DefaultItemService;
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


@RunWith(MockitoJUnitRunner.class)
public class DefaultItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private DefaultItemService defaultItemService;

    private ItemEntity itemEntity;
    private List<ItemEntity> itemEntityList;
    private CategoryEntity categoryEntity;

    @Before
    public void setup(){
        itemEntity = new ItemEntity(50,"cepita","Orange Cpita",50L,null);
        itemEntityList = Arrays.asList(itemEntity,null,itemEntity,null);
        categoryEntity = new CategoryEntity(1,"category","description",null);
    }

    @Test
    public void testShouldReturnListOfAllItems(){
        //arrange
        Mockito.when(itemRepository.findAll()).thenReturn(itemEntityList);
        //act
        List<ItemEntity> expected = defaultItemService.getAllItems();
        //assertEquals
        assertEquals("Expected should return provided list",expected,itemEntityList);
    }

    @Test
    public void testShouldReturnItem(){
        Mockito.when(itemRepository.findById(any())).thenReturn(Optional.of(itemEntity));

        ItemEntity expected = defaultItemService.findItemById(1);

        assertEquals("Expected should return id",expected,itemEntity);
    }

    @Test (expected = NotFoundException.class)
    public void testShouldThrowNotFoundException(){
        Mockito.when(itemRepository.findById(any())).thenReturn(Optional.empty());

        defaultItemService.findItemById(1);
    }

    @Test
    public void testShouldReturnItemByName(){
        Mockito.when(itemRepository.findByName(any())).thenReturn(Optional.of(itemEntity));

        ItemEntity expected = defaultItemService.findItemByName("Franco");

        assertEquals("Expected should match name",expected,itemEntity);
    }

    @Test (expected = NotFoundException.class)
    public void testShouldThrowNotFoundExceptionInFindByName(){
        Mockito.when(itemRepository.findByName(any())).thenReturn(Optional.empty());

        defaultItemService.findItemByName("Franco");
    }

    @Test
    public void testShouldReturnFindItemByCategory(){
        Mockito.when(categoryRepository.findById(any())).thenReturn(Optional.of(categoryEntity));
        Mockito.when(itemRepository.findByCategoryEntities(any())).thenReturn(itemEntityList);

        List<ItemEntity> expected = defaultItemService.findItemByCategory(1);

        assertEquals("Should return category Id",expected,itemEntityList);
    }

    @Test (expected = NotFoundException.class)
    public void testShouldThrowExceptionIfCategoryWasNotFound(){
        Mockito.when(categoryRepository.findById(any())).thenReturn(Optional.empty());

        defaultItemService.findItemByCategory(1);
    }

    @Test
    public void testShouldReturnCreatedItem(){
        Mockito.when(itemRepository.save(any())).thenReturn(itemEntity);

        ItemEntity expected = defaultItemService.createItem(new ItemEntity());

        assertEquals("Expected should match created item",expected,itemEntity);
    }

    @Test
    public void testShouldReturnUpdatedEntity(){
        Mockito.when(itemRepository.findById(any())).thenReturn(Optional.of(new ItemEntity()));
        Mockito.when(itemRepository.save(any())).thenReturn(itemEntity);

        ItemEntity expected = defaultItemService.updateItem(itemEntity);

        assertEquals("Expected should match update",expected,itemEntity);
    }
    @Test (expected = NotFoundException.class)
    public void testShouldThrowNotFoundExceptionWhenEntityDoesNotExists(){
        Mockito.when(itemRepository.findById(any())).thenReturn(Optional.empty());

        defaultItemService.updateItem(new ItemEntity());
    }

    @Test
    public void testShouldReturnNothingWhenDeleted(){
        Mockito.when(itemRepository.findById(any())).thenReturn(Optional.of(new ItemEntity()));

        defaultItemService.deleteItem(1);

        Mockito.verify(itemRepository, Mockito.times(1)).findById(1);
        Mockito.verify(itemRepository,Mockito.times(1)).deleteById(1);
    }

    @Test (expected = NotFoundException.class)
    public void testShouldThrowExceptionWhenDeleteEntityIsNotFound(){
        Mockito.when(itemRepository.findById(any())).thenReturn(Optional.empty());

        defaultItemService.deleteItem(2);
    }
}