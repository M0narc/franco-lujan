package com.bootcamp.shoppingcart.controllers;

import com.bootcamp.shoppingcart.domain.entities.ItemEntity;
import com.bootcamp.shoppingcart.services.implementations.DefaultItemService;
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
public class ItemControllerTest {

    @Mock
    private DefaultItemService defaultItemService;
    @Mock
    private DefaultLoginService defaultLoginService;

    @InjectMocks
    private ItemController itemController;

    private ItemEntity itemEntity;
    private List<ItemEntity> itemEntityList;

    @Before
    public void setup(){
        itemEntity = new ItemEntity(1,"item", "description", 1L, Collections.emptyList());
        itemEntityList = Arrays.asList(itemEntity,null,itemEntity,null);
        Mockito.when(defaultLoginService.validate(any(),any())).thenReturn(true);
    }

    @Test
    public void testShouldReturnAllItems(){
        Mockito.when(defaultItemService.getAllItems()).thenReturn(itemEntityList);

        List<ItemEntity> expected = itemController.getAllItems("nn","qwerty");

        assertEquals("Expected should return all items",expected,itemEntityList);
    }

    @Test
    public void testShouldReturnItemById(){
        Mockito.when(defaultItemService.findItemById(any())).thenReturn(itemEntity);

        ItemEntity expected = itemController.getItemById(1,"nn","qwerty");

        assertEquals("Expected Should return items by id",expected,itemEntity);
    }

    @Test
    public void testShouldReturnItemByName(){
        Mockito.when(defaultItemService.findItemByName(any())).thenReturn(itemEntity);

        ItemEntity expected = itemController.getItemByName("Franco","nn","qwerty");

        assertEquals("Expected should return items by name", expected, itemEntity);
    }

    @Test
    public void testShouldReturnItemByCategory (){
        Mockito.when(defaultItemService.findItemByCategory(any())).thenReturn(itemEntityList);

        List<ItemEntity> expected = itemController.getItemByCategory(2,"nn","qwerty");

        assertEquals("Expected should return item by category",expected,itemEntityList);
    }

    @Test
    public void testShouldReturnCreatedItem(){
        Mockito.when(defaultItemService.createItem(any())).thenReturn(itemEntity);

        ItemEntity expected = itemController.createItem(new ItemEntity(),"nn","qwerty");

        assertEquals("Expected should return new item", expected, itemEntity);
    }

    @Test
    public void testShouldReturnUpdatedItem(){
        Mockito.when(defaultItemService.updateItem(any())).thenReturn(itemEntity);

        ItemEntity expected = itemController.updateItem(itemEntity,"nn","qwerty");

        assertEquals("Expected should return updated item",expected,itemEntity);
    }

    @Test
    public void testShouldReturnNoContentandDeleteItem(){
        itemController.deletItem(1,"nn","qwerty");

        Mockito.verify(defaultItemService,Mockito.times(1)).deleteItem(any());
        Mockito.verify(defaultLoginService,Mockito.times(1)).validate(any(),any());
    }
}