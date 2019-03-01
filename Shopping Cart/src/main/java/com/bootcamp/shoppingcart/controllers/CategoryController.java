package com.bootcamp.shoppingcart.controllers;


import com.bootcamp.shoppingcart.domain.entities.CategoryEntity;
import com.bootcamp.shoppingcart.services.interfaces.CategoryService;
import com.bootcamp.shoppingcart.services.interfaces.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private final CategoryService categoryService;
    private final LoginService loginService;

    public CategoryController(CategoryService categoryService, LoginService loginService) {
        this.categoryService = categoryService;
        this.loginService = loginService;
    }

    @GetMapping("/categories")
    @ResponseStatus(code = HttpStatus.OK)
    public List<CategoryEntity> getAllCategories(@RequestHeader("Auth-userName")String username,@RequestHeader("Auth-pass")String pass){
        loginService.validate(username,pass);
        return categoryService.getAllCategories();
    }

    @GetMapping("/categories/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public CategoryEntity getCategoryById (@PathVariable Integer id,@RequestHeader("Auth-userName")String username,@RequestHeader("Auth-pass")String pass){
        loginService.validate(username, pass);
        return categoryService.getCategoryById(id);
    }

    @PostMapping("/categories")
    @ResponseStatus(code = HttpStatus.OK)
    public CategoryEntity createCategory (@RequestBody CategoryEntity categoryEntity,@RequestHeader("Auth-userName")String username,@RequestHeader("Auth-pass")String pass){
        loginService.validate(username, pass);
        return categoryService.createCategory(categoryEntity);
    }

    @PutMapping("/categories")
    @ResponseStatus(code = HttpStatus.OK)
    public CategoryEntity updateCategory (@RequestBody CategoryEntity categoryEntity,@RequestHeader("Auth-userName")String username,@RequestHeader("Auth-pass")String pass){
        loginService.validate(username,pass);
        return categoryService.updateCategory(categoryEntity);
    }

    @DeleteMapping("/categories/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteCategoryById(@PathVariable Integer id,@RequestHeader("Auth-userName")String username,@RequestHeader("Auth-pass")String pass){
        loginService.validate(username, pass);
        categoryService.deleteCategory(id);
    }

}