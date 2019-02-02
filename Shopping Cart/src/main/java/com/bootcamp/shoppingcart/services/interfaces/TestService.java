package com.bootcamp.shoppingcart.services.interfaces;

import com.bootcamp.shoppingcart.domain.entities.Test;

import java.util.List;

public interface TestService {

    Test createTest (Test test);

    List <Test> getAllTests ();
}
