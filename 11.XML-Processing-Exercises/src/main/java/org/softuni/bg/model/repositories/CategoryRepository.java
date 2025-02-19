package org.softuni.bg.model.repositories;

import org.softuni.bg.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c " +
            "ORDER BY SIZE(c.products) DESC")
    List<Category> findAllByProductsCount();
}
