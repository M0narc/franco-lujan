package com.bootcamp.shoppingcart.services.implementations.services.implementations;

import com.bootcamp.shoppingcart.domain.entities.TestEntity;
import com.bootcamp.shoppingcart.domain.repositories.TestRepository;
import com.bootcamp.shoppingcart.services.implementations.DefaultTestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

//indica que la clase la va a trabajar con el framework de Mockito
@RunWith(MockitoJUnitRunner.class)
public class TestServiceImplTestEntity {

    //Dependency to mock
    @Mock
    private TestRepository testRepository;

    // Class to be tested ALWAYS USE THIS FOR NOW
    @InjectMocks
    private DefaultTestService testService;

    private TestEntity testEntity;

    //Executes before each test method
    @Before
    public void setup(){
        testEntity = new TestEntity(5,"this is an entity");
    }
    @Test
    public  void testShouldReturnNewTestEntity(){
        //Arrange everything the act needs to work
        Mockito.when(testRepository.save(any())).thenReturn(testEntity);
        TestEntity expected =testEntity;
        //runs testing class and saves results in given variable.
        TestEntity actual = testService.createTest(new TestEntity());
        //Assert call necessary assert in order to check expected variables.
        assertEquals(expected,actual);
    }
}