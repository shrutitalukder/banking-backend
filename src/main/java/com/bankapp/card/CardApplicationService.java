package com.bankapp.card;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CardApplicationService {

    private final CardApplicationRepository repository;

    public CardApplicationService(CardApplicationRepository repository) {
        this.repository = repository;
    }

    /**
     * Fetch all card applications of a user
     */
    public List<CardApplication> getApplicationsForUser(String username) {
        return repository.findByUsername(username);
    }

    /**
     * (Optional) Create a new card application
     * This helps us test tracking easily
     */
    public CardApplication applyForCard(String username, String cardType) {

        CardApplication app = new CardApplication();
        app.setUsername(username);
        app.setCardType(cardType);
        app.setStatus(CardStatus.APPLIED);
        app.setAppliedAt(LocalDateTime.now());

        return repository.save(app);
    }
}
