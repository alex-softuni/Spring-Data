package org.example.springdataintrolab.services;

import org.example.springdataintrolab.models.Account;
import org.example.springdataintrolab.models.User;
import org.example.springdataintrolab.repositories.AccountRepository;
import org.example.springdataintrolab.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public UserServiceImpl(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void registerUser(User user) {
        if (!userRepository.existsById(user.getId())) {
            userRepository.save(user);
        }
    }

    @Override
    public Optional<Account> findById(int id) {
        return accountRepository.findById(id);
    }
}
