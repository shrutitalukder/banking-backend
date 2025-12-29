package com.bankapp.card;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardApplicationRepository
        extends JpaRepository<CardApplication, Long> {

    List<CardApplication> findByUsername(String username);
}
