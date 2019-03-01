package com.bootcamp.shoppingcart.services.implementations;

import com.bootcamp.shoppingcart.constants.ExceptionMessages;
import com.bootcamp.shoppingcart.domain.entities.UserEntity;
import com.bootcamp.shoppingcart.domain.repositories.UserRepository;
import com.bootcamp.shoppingcart.exceptions.NotFoundException;
import com.bootcamp.shoppingcart.services.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.GregorianCalendar;
import java.util.List;

@Service
public class DefaultUserService implements UserService {

    private UserRepository userRepository;


    public DefaultUserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        userEntity.setId(null);
        return userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessages.NOT_FOUND_USER.getString()));
    }

    @Override
    public UserEntity getUserByName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new NotFoundException (ExceptionMessages.NOT_FOUND_USER.getString()));
    }

    @Override
    public UserEntity updateUser(UserEntity user) {
        userRepository.findById(user.getId())
                .orElseThrow(() -> new NotFoundException(ExceptionMessages.NOT_FOUND_USER.getString()));
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessages.NOT_FOUND_USER.getString()));
        userRepository.deleteById(id);
    }

    @Override
    public UserEntity updateUserLastSeen(UserEntity userEntity) {
        userEntity.setLastSeen(GregorianCalendar.getInstance());
        return userRepository.save(userEntity);
    }


}
