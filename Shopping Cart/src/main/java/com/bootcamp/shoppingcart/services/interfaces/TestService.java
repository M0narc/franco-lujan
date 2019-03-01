package com.bootcamp.shoppingcart.services.interfaces;

import com.bootcamp.shoppingcart.domain.entities.TestEntity;

import java.util.List;

public interface TestService {

    TestEntity createTest (TestEntity testEntity);

    List <TestEntity> getAllTests ();
}
