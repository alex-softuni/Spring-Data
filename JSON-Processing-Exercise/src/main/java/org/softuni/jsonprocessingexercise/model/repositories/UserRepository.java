package org.softuni.jsonprocessingexercise.model.repositories;

import org.softuni.jsonprocessingexercise.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u.id FROM User u")
    List<Long> findAllIds();

    @Query("SELECT u FROM User u " +
            "JOIN u.sold p WHERE p.buyer IS NOT NULL AND SIZE(u.sold) > 0 "
    )
    List<User> findAllSoldProductsWithBuyer();

}
