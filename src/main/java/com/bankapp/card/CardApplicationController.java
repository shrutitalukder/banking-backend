package com.bankapp.card;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cards")
public class CardApplicationController {

    private final CardApplicationService service;

    public CardApplicationController(CardApplicationService service) {
        this.service = service;
    }

    /**
     * Apply for a new card (helper API for testing)
     */
    @PostMapping("/apply")
    public CardApplication applyForCard(
            @RequestParam String cardType,
            Authentication authentication) {

        String username = authentication.getName();
        return service.applyForCard(username, cardType);
    }

    /**
     * Track card application status
     */
    @GetMapping("/status")
    public List<CardApplication> trackCardStatus(
            Authentication authentication) {

        String username = authentication.getName();
        return service.getApplicationsForUser(username);
    }
}
