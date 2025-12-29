package com.bankapp.transaction;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * IMPS Transfer
     */
    @PostMapping("/imps")
    public Transaction impsTransfer(
            @RequestBody ImpsRequest request,
            Authentication authentication) {

        // username available if needed
        String username = authentication.getName();

        return transactionService.transferMoney(
                request.getFromAccount(),
                request.getToAccount(),
                request.getAmount());
    }
}
