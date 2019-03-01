package com.bootcamp.shoppingcart.domain.repositories;

import com.bootcamp.shoppingcart.domain.entities.CategoryEntity;
import com.bootcamp.shoppingcart.domain.entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {

    Optional<ItemEntity> findByName(String name);

    List<ItemEntity> findByCategoryEntities(CategoryEntity categoryEntity);
}
