package com.bootcamp.shoppingcart.services.interfaces;

import com.bootcamp.shoppingcart.domain.entities.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> getAllUsers();

    UserEntity createUser(UserEntity userName);

    UserEntity getUserByName(String userName);

    UserEntity updateUser(UserEntity user);

    UserEntity updateUserLastSeen (UserEntity userEntity);

    UserEntity getUserById(Long id);

    void deleteUserById(Long id);
}
