package com.bootcamp.shoppingcart.services.implementations;

import com.bootcamp.shoppingcart.domain.entities.TestEntity;
import com.bootcamp.shoppingcart.domain.repositories.TestRepository;
import com.bootcamp.shoppingcart.services.interfaces.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
// indica que es un componente de spring y que es un service
@Service
public class DefaultTestService implements TestService {

    private TestRepository testRepository;
    //hace la injeccion de dependencia
    @Autowired
    public DefaultTestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public TestEntity createTest(TestEntity testEntity) {
        return testRepository.save(testEntity);
    }

    @Override
    public List<TestEntity> getAllTests() {
        return testRepository.findAll();
    }
}
