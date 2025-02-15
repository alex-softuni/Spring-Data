package org.example.springdataintrolab.services;

import org.example.springdataintrolab.models.Account;
import org.example.springdataintrolab.models.User;

import java.util.Optional;

public interface UserService {
    void registerUser(User user);
    Optional<Account> findById(int id);
}
