package org.example.springdataintrolab;

import org.example.springdataintrolab.models.Account;
import org.example.springdataintrolab.models.User;
import org.example.springdataintrolab.services.AccountService;
import org.example.springdataintrolab.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private UserService userService;
    private AccountService accountService;

    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {

        User user = new User("Pesho", 20);
        Account account = new Account(new BigDecimal(25000));
        account.setUser(user);

        user.setAccounts(new ArrayList<>() {{
            add(account);
        }});

        userService.registerUser(user);

        accountService.withdrawMoney(new BigDecimal(20000), account.getId());
        accountService.transferMoney(new BigDecimal(30000), account.getId());
    }
}
