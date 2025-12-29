package com.bankapp.fd;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FixedDepositRepository
        extends JpaRepository<FixedDeposit, Long> {

    // For user view
    List<FixedDeposit> findByUsername(String username);

    // For scheduler (matured FDs)
    List<FixedDeposit> findByMaturityDateLessThanEqualAndStatus(
            LocalDate date, String status);
}
