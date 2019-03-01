package com.bootcamp.shoppingcart.controllers;


import com.bootcamp.shoppingcart.domain.entities.ItemEntity;
import com.bootcamp.shoppingcart.services.interfaces.ItemService;
import com.bootcamp.shoppingcart.services.interfaces.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {


    private final ItemService itemService;
    private final LoginService loginService;

    public ItemController(ItemService itemService, LoginService loginService) {
        this.itemService = itemService;
        this.loginService = loginService;
    }

    @GetMapping("/items")
    @ResponseStatus(code = HttpStatus.OK)
    public List<ItemEntity> getAllItems(@RequestHeader("Auth-userName")String username,@RequestHeader("Auth-pass")String pass){
        loginService.validate(username,pass);
        return itemService.getAllItems();
    }

    @GetMapping("/items/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ItemEntity getItemById (@PathVariable Integer id,@RequestHeader("Auth-userName")String username,@RequestHeader("Auth-pass")String pass){
        loginService.validate(username,pass);
        return itemService.findItemById(id);
    }

    @GetMapping("/items/name/{name}")
    @ResponseStatus(code = HttpStatus.OK)
    public ItemEntity getItemByName (@PathVariable String name,@RequestHeader("Auth-userName")String username,@RequestHeader("Auth-pass")String pass){
        loginService.validate(username,pass);
        return itemService.findItemByName(name);
    }

    @GetMapping("/items/category/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<ItemEntity> getItemByCategory (@PathVariable Integer id,@RequestHeader("Auth-userName")String username,@RequestHeader("Auth-pass")String pass){
        loginService.validate(username,pass);
        return itemService.findItemByCategory(id);
    }

    @PostMapping("/items")
    @ResponseStatus(code = HttpStatus.OK)
    public ItemEntity createItem (@RequestBody ItemEntity itemEntity,@RequestHeader("Auth-userName")String username,@RequestHeader("Auth-pass")String pass){
        loginService.validate(username,pass);
       return itemService.createItem(itemEntity);
    }

    @PutMapping("/items")
    @ResponseStatus(code = HttpStatus.OK)
    public ItemEntity updateItem(@RequestBody ItemEntity itemEntity,@RequestHeader("Auth-userName")String username,@RequestHeader("Auth-pass")String pass){
        loginService.validate(username,pass);
        return itemService.updateItem(itemEntity);
    }

    @DeleteMapping("/items/id")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletItem (@PathVariable Integer id,@RequestHeader("Auth-userName")String username,@RequestHeader("Auth-pass")String pass){
        loginService.validate(username,pass);
        itemService.deleteItem(id);
    }

}
