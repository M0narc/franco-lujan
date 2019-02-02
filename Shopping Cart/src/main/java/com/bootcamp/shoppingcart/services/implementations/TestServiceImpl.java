package com.bootcamp.shoppingcart.services.implementations;

import com.bootcamp.shoppingcart.domain.entities.Test;
import com.bootcamp.shoppingcart.repositories.TestRepository;
import com.bootcamp.shoppingcart.services.interfaces.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    private TestRepository testRepository;

    @Autowired
    public TestServiceImpl(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public Test createTest(Test test) {
        return testRepository.save(test);
    }

    @Override
    public List<Test> getAllTests() {
        return testRepository.findAll();
    }
}
