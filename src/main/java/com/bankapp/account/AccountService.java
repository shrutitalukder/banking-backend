package com.bankapp.account;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    // constructor injection (recommended)
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Fetch all accounts for the logged-in user
     */
    public List<Account> getAccountsForUser(String username) {
        return accountRepository.findByUsername(username);
    }
}
