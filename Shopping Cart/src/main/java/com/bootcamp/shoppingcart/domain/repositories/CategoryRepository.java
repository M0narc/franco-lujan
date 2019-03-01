package com.bootcamp.shoppingcart.domain.repositories;

import com.bootcamp.shoppingcart.domain.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository <CategoryEntity, Integer> {
}
