package com.bootcamp.shoppingcart.domain.repositories;


import com.bootcamp.shoppingcart.domain.entities.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//indica que la clase es un componente de spring y que es un repositorio
@Repository
public interface TestRepository extends JpaRepository <TestEntity,Integer>{

}
