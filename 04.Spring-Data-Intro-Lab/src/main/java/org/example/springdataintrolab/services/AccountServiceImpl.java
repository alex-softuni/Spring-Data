package org.example.springdataintrolab.services;

import org.example.springdataintrolab.models.Account;
import org.example.springdataintrolab.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal money, int id) {
        Account account = accountRepository.findById(id).get();
        if (account.getBalance().compareTo(money) >= 0) {
            account.setBalance(account.getBalance().subtract(money));
            accountRepository.save(account);
            System.out.println("Successfully withdrawn " + money + "$");
        } else {
            System.out.println("Insufficient funds");
        }
    }

    @Override
    public void transferMoney(BigDecimal money, int id) {
        Account account = accountRepository.findById(id).get();
        if (money.compareTo(BigDecimal.ZERO) >= 0) {
            account.setBalance(account.getBalance().add(money));
            accountRepository.save(account);
            System.out.println("Successfully added " + money + "$");
        }
    }


}
