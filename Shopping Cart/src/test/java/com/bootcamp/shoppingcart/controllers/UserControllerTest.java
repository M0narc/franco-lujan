package com.bootcamp.shoppingcart.controllers;

import com.bootcamp.shoppingcart.domain.entities.UserEntity;
import com.bootcamp.shoppingcart.services.implementations.DefaultUserService;
import com.bootcamp.shoppingcart.services.implementations.DefaultLoginService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private DefaultUserService defaultUserService;
    @Mock
    private DefaultLoginService defaultLoginService;

    @InjectMocks
    private UserController userController;

    private UserEntity userEntity;
    private List<UserEntity> userEntityList;

    @Before
    public void setup(){
        userEntity = new UserEntity (1L,"Monarc","Franco","Lujan","francomatiaslujan23@gmail.com","qwerty","3624123456",new GregorianCalendar(),new GregorianCalendar(), Collections.emptyList());
        userEntityList = Arrays.asList(userEntity, null , userEntity, null);
        Mockito.when(defaultLoginService.validate(any(),any())).thenReturn(true);
    }

    @Test
    public void testShouldReturnAllUsers(){
        Mockito.when(defaultUserService.getAllUsers()).thenReturn(userEntityList);

        List<UserEntity> expected = userController.getAllUsers("nn","qwerty");

        assertEquals("Expected should return all users",expected,userEntityList);
    }

    @Test
    public void testShouldReturnUserById(){
        Mockito.when(defaultUserService.getUserById(any())).thenReturn(userEntity);

        UserEntity expected = userController.getUser(1L,"nn","qwerty");

        assertEquals("Expected should return user by id", expected, userEntity);
    }

    @Test
    public void testShouldReturnUserByName(){
        Mockito.when(defaultUserService.getUserByName(any())).thenReturn(userEntity);

        UserEntity expected = userController.getUserByName("Franco","nn","qwerty");

        assertEquals("Expected should return user by name", expected, userEntity);
    }

    @Test
    public void testShouldReturnCreatedUser(){
        Mockito.when(defaultUserService.createUser(any())).thenReturn(userEntity);

        UserEntity expected = userController.createUser(new UserEntity());

        assertEquals("Expected should return new user", expected, userEntity);
    }

    @Test
    public void testShouldReturnUpdatedUser (){
        Mockito.when(defaultUserService.updateUser(any())).thenReturn(userEntity);

        UserEntity expected = userController.updateUserById(userEntity,"nn","qwerty");

        assertEquals("Expected should return updated user", expected, userEntity);
    }

    @Test
    public void testShouldReturnEmptyAndDeleteUser(){
        userController.deleteUser(1L,"nn","qwerty");

        Mockito.verify(defaultUserService,Mockito.times(1)).deleteUserById(1L);
        Mockito.verify(defaultLoginService,Mockito.times(1)).validate(any(),any());
    }
}