package com.bankapp.account;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * View all accounts of the logged-in user
     */
    @GetMapping
    public List<Account> viewMyAccounts(Authentication authentication) {

        // username comes from JWT (SecurityContext)
        String username = authentication.getName();

        return accountService.getAccountsForUser(username);
    }
}
