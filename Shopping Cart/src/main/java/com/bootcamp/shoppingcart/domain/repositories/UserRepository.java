package com.bootcamp.shoppingcart.domain.repositories;

import com.bootcamp.shoppingcart.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <UserEntity,Long> {

    Optional<UserEntity> findByUserName(String userName);

}
