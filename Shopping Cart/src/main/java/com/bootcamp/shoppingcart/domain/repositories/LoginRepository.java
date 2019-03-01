package com.bootcamp.shoppingcart.domain.repositories;

import com.bootcamp.shoppingcart.domain.entities.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Long>{

    Optional<LoginEntity> findByUserName(String username);

    boolean existsByUserName(String username);
}
