package com.bootcamp.shoppingcart.controllers;


import com.bootcamp.shoppingcart.domain.entities.Test;
import com.bootcamp.shoppingcart.services.interfaces.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    private TestService testService;


    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/test")
    public ResponseEntity<List<Test>> getAllTests(){
        return ResponseEntity.ok(testService.getAllTests());
    }

    @PostMapping("/test")
    public ResponseEntity<Test> postTest(@RequestBody Test test){
        return ResponseEntity.ok(testService.createTest(test));
    }
}
