package com.bootcamp.shoppingcart.controllers;

import com.bootcamp.shoppingcart.domain.entities.LoginEntity;
import com.bootcamp.shoppingcart.services.implementations.DefaultLoginService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    @Mock
    private DefaultLoginService defaultLoginService;

    @InjectMocks
    private LoginController loginController;

    private LoginEntity loginEntity;

    @Before
    public void setup(){
        loginEntity = new LoginEntity(1L,"Test","qwerty");
    }

    @Test
    public void  testShouldLoginUser (){
        Mockito.when(defaultLoginService.login(any(),any())).thenReturn(true);

        loginController.loginUser(loginEntity);

        Mockito.verify(defaultLoginService,Mockito.times(1)).login(any(),any());
    }

    @Test
    public void testShouldLogOut(){
        Mockito.when(defaultLoginService.logout(any(),any())).thenReturn(true);

        loginController.logOutUser(loginEntity);

        Mockito.verify(defaultLoginService,Mockito.times(1)).logout(any(),any());
    }

}