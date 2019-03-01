package com.bootcamp.shoppingcart.services.interfaces;

import com.bootcamp.shoppingcart.domain.entities.ItemEntity;

import java.util.List;

public interface ItemService {

    List<ItemEntity> getAllItems();

    ItemEntity findItemById(Integer id);

    ItemEntity findItemByName(String name);

    List<ItemEntity> findItemByCategory(Integer id);

    ItemEntity createItem (ItemEntity itemEntity);

    ItemEntity updateItem (ItemEntity itemEntity);

    void deleteItem (Integer id);
}
