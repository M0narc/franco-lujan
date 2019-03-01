package com.bootcamp.shoppingcart.controllers;

import com.bootcamp.shoppingcart.domain.entities.LoginEntity;
import com.bootcamp.shoppingcart.services.interfaces.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/login")
    public void loginUser(@RequestBody LoginEntity loginEntity){
        loginService.login(loginEntity.getUserName(),loginEntity.getPass());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/logout")
    public void logOutUser(@RequestBody LoginEntity loginEntity){
        loginService.logout(loginEntity.getUserName(),loginEntity.getPass());
    }
}
