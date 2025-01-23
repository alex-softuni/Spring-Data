package org.example.springdataintrolab.repositories;

import org.example.springdataintrolab.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    User findByAge(int age);

    List<User> findAllByAge(int age);



}
