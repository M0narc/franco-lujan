package com.bootcamp.shoppingcart.services.implementations.services.implementations;

import com.bootcamp.shoppingcart.domain.entities.UserEntity;
import com.bootcamp.shoppingcart.domain.repositories.UserRepository;
import com.bootcamp.shoppingcart.exceptions.NotFoundException;
import com.bootcamp.shoppingcart.services.implementations.DefaultUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DefaultUserService defaultUserService;

    private UserEntity userEntity;
    private List<UserEntity> userEntityList;

    @Before
    public void setup(){
        userEntity = new UserEntity (1L,"Monarc","Franco","Lujan","francomatiaslujan@gmail.com","SOAD","3624123456", new GregorianCalendar(), new GregorianCalendar(),Collections.emptyList());
        userEntityList = Arrays.asList(userEntity, null,userEntity,null);
    }

    @Test
    public void testShouldReturnCreatedUser(){
        Mockito.when(userRepository.save(any())).thenReturn(userEntity);

        UserEntity expected = defaultUserService.createUser(new UserEntity());

        assertEquals("Expected should return new user",expected,userEntity);
    }

    @Test
    public void testShouldReturnAllUsers(){
        Mockito.when(userRepository.findAll()).thenReturn(userEntityList);

        List<UserEntity> expect = defaultUserService.getAllUsers();

        assertEquals("Expected Should return all users",expect,userEntityList);
    }

    @Test
    public void testShouldReturnUsersById (){
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(userEntity));

        UserEntity expect = defaultUserService.getUserById(1L);

        assertEquals("Expected should return items by id",expect,userEntity);
    }

    @Test (expected = NotFoundException.class)
    public void testShouldThrowExceptionIfNoUserIsFound(){
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.empty());

        defaultUserService.getUserById(1L);
    }

    @Test
    public void testShouldReturnUserByName(){
        Mockito.when(userRepository.findByUserName(any())).thenReturn(Optional.of(userEntity));

        UserEntity expect = defaultUserService.getUserByName("Franco");

        assertEquals("Expect should return user by name",expect,userEntity);
    }

    @Test (expected = NotFoundException.class)
    public void testShouldThrowExceptionIfNoUserNameIsFound(){
        Mockito.when(userRepository.findByUserName(any())).thenReturn(Optional.empty());

        defaultUserService.getUserByName("Franco");
    }

    @Test
    public void testShoulreturnUpdatedUserName(){
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(new UserEntity()));
        Mockito.when(userRepository.save(any())).thenReturn(userEntity);

        UserEntity expected = defaultUserService.updateUser(userEntity);

        assertEquals("Expected should return updated user",expected,userEntity);
    }

    @Test (expected = NotFoundException.class)
    public void testShouldThrowExceptionWhenNoUserIsFoundWhileUpdating(){
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.empty());

        defaultUserService.updateUser(userEntity);
    }

    @Test
    public void testShouldDeleteItem (){
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(new UserEntity()));

        defaultUserService.deleteUserById(1L);

        Mockito.verify(userRepository,Mockito.times(1)).findById(1L);
        Mockito.verify(userRepository,Mockito.times(1)).deleteById(1L);
    }

    @Test (expected = NotFoundException.class)
    public void testShouldThrowExceptionWhenNoUserIsFoundWhileTryingToDelete(){
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.empty());

        defaultUserService.deleteUserById(2L);
    }

    @Test
    public void testShouldUpdateCalendar(){
        Mockito.when(userRepository.save(any())).thenReturn(userEntity);

        UserEntity expected = defaultUserService.updateUserLastSeen(new UserEntity());

        assertEquals("Expected should return updated calendar", expected, userEntity);
    }

}