package com.bootcamp.shoppingcart.controllers;


import com.bootcamp.shoppingcart.domain.entities.TestEntity;
import com.bootcamp.shoppingcart.services.interfaces.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
// it tells spring that this is a component and it is a Rest controller
@RestController
public class TestController {

    private TestService testService;

    //dependency injections
    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    //it tells spring that this is and endpoint and it accepts verbs http verb get
    @GetMapping("/test")
    public ResponseEntity<List<TestEntity>> getAllTests(){
        return ResponseEntity.ok(testService.getAllTests());
    }

    @PostMapping("/testEntity")
    public ResponseEntity<TestEntity> postTest(@RequestBody TestEntity testEntity){
        return ResponseEntity.ok(testService.createTest(testEntity));
    }
}
