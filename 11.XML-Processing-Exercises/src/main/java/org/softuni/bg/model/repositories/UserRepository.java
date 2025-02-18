package org.softuni.bg.model.repositories;

import org.softuni.bg.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u.id FROM User u")
    List<Long> findAllIds();
}
