package org.example.springdataintrolab.services;


import org.example.springdataintrolab.models.Account;

import java.math.BigDecimal;

public interface AccountService {
    void withdrawMoney(BigDecimal money, int id);
    void transferMoney(BigDecimal money, int id);
}
