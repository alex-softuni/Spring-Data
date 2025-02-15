package org.softuni.jsonprocessingexercise.model.repositories;

import org.softuni.jsonprocessingexercise.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCase(String normalizedCategoryName);

    @Query("SELECT c.id FROM Category c")
    List<Long> findAllIds();
}
