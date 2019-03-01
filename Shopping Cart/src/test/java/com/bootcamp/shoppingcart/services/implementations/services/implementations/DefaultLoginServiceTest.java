package com.bootcamp.shoppingcart.services.implementations.services.implementations;

import com.bootcamp.shoppingcart.domain.entities.LoginEntity;
import com.bootcamp.shoppingcart.domain.entities.UserEntity;
import com.bootcamp.shoppingcart.domain.repositories.LoginRepository;
import com.bootcamp.shoppingcart.exceptions.BadRequestException;
import com.bootcamp.shoppingcart.exceptions.UnauthorizedException;
import com.bootcamp.shoppingcart.services.implementations.DefaultLoginService;
import com.bootcamp.shoppingcart.services.interfaces.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class DefaultLoginServiceTest {

    @Mock
    private LoginRepository loginRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private DefaultLoginService defaultLoginService;

    private LoginEntity loginEntity;
    private UserEntity userEntity;
    private List<UserEntity> userEntityList;

    @Before
    public void setup (){
        loginEntity = new LoginEntity(1L,"Test","qwerty");
        userEntity = new UserEntity (1L,"Monarc","Franco","Lujan","francomatiaslujan23@gmail.com","qwerty","3624123456",new GregorianCalendar(),new GregorianCalendar(), Collections.emptyList());
        userEntityList = Arrays.asList(userEntity, null , userEntity, null);
    }

    @Test
    public void testShouldLogin (){
        Mockito.when(userService.getUserByName(any())).thenReturn(userEntity);
        Mockito.when(loginRepository.existsByUserName(any())).thenReturn(false);
        Mockito.when(userService.updateUserLastSeen(any())).thenReturn(userEntity);
        Mockito.when(loginRepository.save(any())).thenReturn(loginEntity);

        boolean expected = defaultLoginService.login("Franco","qwerty");

        assertTrue("expected should login",expected);
        Mockito.verify(userService,Mockito.times(1)).getUserByName(any());
        Mockito.verify(loginRepository,Mockito.times(1)).existsByUserName(any());
        Mockito.verify(userService,Mockito.times(1)).updateUserLastSeen(any());
        Mockito.verify(loginRepository,Mockito.times(1)).save(any());
    }

    @Test (expected = UnauthorizedException.class)
    public void testShouldThrowExceptionWhenUserIsAlredyLogedIn(){
        Mockito.when(userService.getUserByName(any())).thenReturn(userEntity);
        Mockito.when(loginRepository.existsByUserName(any())).thenReturn(true);

        defaultLoginService.login("Franco","dsaasd");


    }

    @Test
    public void testShouldLogOut(){
        Mockito.when(loginRepository.findByUserName(any())).thenReturn(Optional.of(loginEntity));
        Mockito.when(loginRepository.existsByUserName(any())).thenReturn(true);
        Mockito.when(userService.updateUserLastSeen(any())).thenReturn(userEntity);
        Mockito.when(userService.getUserByName(any())).thenReturn(userEntity);

        boolean expected = defaultLoginService.logout("Franco","qwerty");

        assertTrue("Expected should logout",expected);
        Mockito.verify(loginRepository,Mockito.times(1)).findByUserName(any());
        Mockito.verify(loginRepository,Mockito.times(1)).existsByUserName(any());
        Mockito.verify(userService,Mockito.times(1)).updateUserLastSeen(any());
        Mockito.verify(userService,Mockito.times(1)).getUserByName(any());
    }

    @Test (expected = BadRequestException.class)
    public void testShouldThrowExceptionWhenUserNameNotFound(){
        Mockito.when(loginRepository.findByUserName(any())).thenReturn(Optional.empty());

        defaultLoginService.logout("Franco","qwerty");
    }

    @Test(expected = UnauthorizedException.class)
    public void testShouldThrowExceptionWhenNotAuthorized(){
        Mockito.when(loginRepository.findByUserName(any())).thenReturn(Optional.of(loginEntity));
        Mockito.when(loginRepository.existsByUserName(any())).thenReturn(false);

        defaultLoginService.logout("Franco","qwerty");
    }

    @Test
    public void testShouldValidate(){
        Mockito.when(loginRepository.findByUserName(any())).thenReturn(Optional.of(loginEntity));
        Mockito.when(userService.updateUserLastSeen(any())).thenReturn(userEntity);
        Mockito.when(userService.getUserByName(any())).thenReturn(userEntity);

        boolean expected = defaultLoginService.validate("Franco","qwerty");

        assertTrue("Expected should logout",expected);
        Mockito.verify(loginRepository,Mockito.times(1)).findByUserName(any());
        Mockito.verify(userService,Mockito.times(1)).updateUserLastSeen(any());
        Mockito.verify(userService,Mockito.times(1)).getUserByName(any());
    }

    @Test(expected = BadRequestException.class)
    public void testShouldThrowExceptionWhenUserNotFound(){
        Mockito.when(loginRepository.findByUserName(any())).thenReturn(Optional.empty());

        defaultLoginService.validate("Franco","qwerty");
    }

    @Test(expected = UnauthorizedException.class)
    public void testShouldThrowExceptionWhenNotValidated(){
        Mockito.when(loginRepository.findByUserName(any())).thenReturn(Optional.of(loginEntity));

        defaultLoginService.validate("Franco","wedsafdas");
    }
}