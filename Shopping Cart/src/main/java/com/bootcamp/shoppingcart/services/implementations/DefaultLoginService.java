package com.bootcamp.shoppingcart.services.implementations;

import com.bootcamp.shoppingcart.constants.ExceptionMessages;
import com.bootcamp.shoppingcart.domain.entities.LoginEntity;
import com.bootcamp.shoppingcart.domain.entities.UserEntity;
import com.bootcamp.shoppingcart.domain.repositories.LoginRepository;
import com.bootcamp.shoppingcart.exceptions.NotFoundException;
import com.bootcamp.shoppingcart.exceptions.UnauthorizedException;
import com.bootcamp.shoppingcart.services.interfaces.LoginService;
import com.bootcamp.shoppingcart.services.interfaces.UserService;
import org.springframework.stereotype.Service;

@Service
public class DefaultLoginService implements LoginService {

    private final UserService userService;
    private final LoginRepository loginRepository;

    public DefaultLoginService(UserService userService, LoginRepository loginRepository) {
        this.userService = userService;
        this.loginRepository = loginRepository;
    }

    @Override
    public boolean login(String username, String pass){
        UserEntity user  = userService.getUserByName(username);
        if(!loginRepository.existsByUserName(username) && user.getPass().equals(pass)){
            userService.updateUserLastSeen(user);
            loginRepository.save(new LoginEntity(null, username, pass));
            return true;
        }
        throw new UnauthorizedException(ExceptionMessages.UnauthorizedException.getString());
    }

    @Override
    public boolean logout(String username,String pass){
        LoginEntity user = loginRepository.findByUserName(username).orElseThrow(() -> new NotFoundException(ExceptionMessages.NOT_FOUND_USER.getString()));
        if (loginRepository.existsByUserName(username) && user.getPass().equals(pass)){
            userService.updateUserLastSeen( userService.getUserByName(username));
            loginRepository.delete(user);
            return true;
        }
        throw new UnauthorizedException(ExceptionMessages.UnauthorizedException.getString());
    }

    @Override
    public boolean validate(String username, String pass){
        LoginEntity user = loginRepository.findByUserName(username).orElseThrow((() -> new NotFoundException (ExceptionMessages.NOT_FOUND_USER.getString())));
        if(user.getPass().equals(pass)){
            userService.updateUserLastSeen( userService.getUserByName(username));
            return true;
        }
        throw new UnauthorizedException(ExceptionMessages.UnauthorizedException.getString());
    }
}