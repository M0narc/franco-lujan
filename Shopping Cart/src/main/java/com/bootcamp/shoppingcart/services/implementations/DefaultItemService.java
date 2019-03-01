package com.bootcamp.shoppingcart.services.implementations;

import com.bootcamp.shoppingcart.constants.ExceptionMessages;
import com.bootcamp.shoppingcart.domain.entities.CategoryEntity;
import com.bootcamp.shoppingcart.domain.entities.ItemEntity;
import com.bootcamp.shoppingcart.domain.repositories.CategoryRepository;
import com.bootcamp.shoppingcart.domain.repositories.ItemRepository;
import com.bootcamp.shoppingcart.exceptions.NotFoundException;
import com.bootcamp.shoppingcart.services.interfaces.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultItemService implements ItemService {

    private ItemRepository itemRepository;
    private CategoryRepository categoryRepository;

    public DefaultItemService(ItemRepository itemRepository, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ItemEntity> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public ItemEntity findItemById(Integer id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessages.NOT_FOUND_ITEM.getString()));
    }

    @Override
    public ItemEntity findItemByName(String name) {
        return itemRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(ExceptionMessages.NOT_FOUND_ITEM.getString()));
    }

    @Override  //make unitaryTest later
    public List<ItemEntity> findItemByCategory(Integer id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessages.NOT_FOUND_CATEGORY.getString()));
        return itemRepository.findByCategoryEntities(categoryEntity);
    }

    @Override
    public ItemEntity createItem(ItemEntity itemEntity) {
        itemEntity.setId(null);
        return itemRepository.save(itemEntity);
    }

    @Override
    public ItemEntity updateItem(ItemEntity itemEntity) {
        itemRepository.findById(itemEntity.getId())
                .orElseThrow(() -> new NotFoundException(ExceptionMessages.NOT_FOUND_ITEM.getString()));
        return itemRepository.save(itemEntity);
    }

    @Override
    public void  deleteItem(Integer id) {
        itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessages.NOT_FOUND_ITEM.getString()));
        itemRepository.deleteById(id);
    }
}
