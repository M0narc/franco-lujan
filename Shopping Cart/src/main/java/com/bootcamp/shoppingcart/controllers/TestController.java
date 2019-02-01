package com.bootcamp.shoppingcart.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<String> testGet(){
        return ResponseEntity.ok("Test for Franco");
    }
}
