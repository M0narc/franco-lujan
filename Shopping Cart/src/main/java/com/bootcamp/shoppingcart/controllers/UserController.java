package com.bootcamp.shoppingcart.controllers;

import com.bootcamp.shoppingcart.domain.entities.UserEntity;
import com.bootcamp.shoppingcart.services.interfaces.LoginService;
import com.bootcamp.shoppingcart.services.interfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {


    private final UserService userService;
    private final LoginService loginService;

    public UserController(UserService userService, LoginService loginService) {
        this.userService = userService;
        this.loginService = loginService;
    }

    @GetMapping ("/users")
    @ResponseStatus(code = HttpStatus.OK)
    public List<UserEntity> getAllUsers(@RequestHeader("Auth-userName")String username,@RequestHeader("Auth-pass")String pass){
        loginService.validate(username,pass);
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public UserEntity getUser (@PathVariable Long id,@RequestHeader("Auth-userName")String username,@RequestHeader("Auth-pass")String pass){
        loginService.validate(username,pass);
        return userService.getUserById(id);
    }

    @GetMapping("/user/name/{userName}")
    @ResponseStatus(code = HttpStatus.OK)
    public UserEntity getUserByName (@PathVariable String userName,@RequestHeader("Auth-userName")String username,@RequestHeader("Auth-pass")String pass){
        loginService.validate(username,pass);
        return userService.getUserByName(userName);
    }

    //NoRequireHeaders
    @PostMapping("/users")
    @ResponseStatus(code = HttpStatus.OK)
    public UserEntity createUser(@RequestBody UserEntity userEntity){
        return userService.createUser(userEntity);
    }

    @PutMapping("/users")
    @ResponseStatus(code = HttpStatus.OK)
    public UserEntity updateUserById(@RequestBody UserEntity userEntity,@RequestHeader("Auth-userName")String username,@RequestHeader("Auth-pass")String pass){
        loginService.validate(username,pass);
        return userService.updateUser(userEntity);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteUser (@PathVariable Long id,@RequestHeader("Auth-userName")String username,@RequestHeader("Auth-pass")String pass){
        loginService.validate(username,pass);
         userService.deleteUserById(id);
    }
}