package com.bankapp.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    // fetch accounts for a specific logged-in user
    List<Account> findByUsername(String username);
    Account findByAccountNumber(String accountNumber);
}
