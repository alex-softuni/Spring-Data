package org.softuni.jsonprocessingexercise.model.repositories;

import org.softuni.jsonprocessingexercise.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
