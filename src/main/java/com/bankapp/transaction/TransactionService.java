package com.bankapp.transaction;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankapp.account.Account;
import com.bankapp.account.AccountRepository;

import com.bankapp.exception.BankingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class TransactionService {
	private static final Logger logger =
	        LogManager.getLogger(TransactionService.class);

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(AccountRepository accountRepository,
                              TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    /**
     * IMPS money transfer
     */
    @Transactional
    public Transaction transferMoney(
            String fromAccountNumber,
            String toAccountNumber,
            Double amount) {

        logger.info("IMPS transfer initiated from {} to {} for amount {}",
                fromAccountNumber, toAccountNumber, amount);

        Account fromAccount =
                accountRepository.findByAccountNumber(fromAccountNumber);

        Account toAccount =
                accountRepository.findByAccountNumber(toAccountNumber);

        if (fromAccount == null || toAccount == null) {
            logger.error("Invalid account number");
            throw new BankingException("Invalid account number");
        }

        if (fromAccount.getBalance() < amount) {
            logger.warn("Insufficient balance in account {}", fromAccountNumber);
            throw new BankingException("Insufficient balance");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction tx = new Transaction();
        tx.setFromAccount(fromAccountNumber);
        tx.setToAccount(toAccountNumber);
        tx.setAmount(amount);
        tx.setStatus("SUCCESS");
        tx.setTimestamp(LocalDateTime.now());

        logger.info("IMPS transfer successful");

        return transactionRepository.save(tx);
    }
}