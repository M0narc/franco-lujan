package com.bootcamp.shoppingcart.repositories;


import com.bootcamp.shoppingcart.domain.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository <Test,Integer>{

}
