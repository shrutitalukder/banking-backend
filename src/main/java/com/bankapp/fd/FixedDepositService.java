package com.bankapp.fd;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FixedDepositService {

    private final FixedDepositRepository repository;

    public FixedDepositService(FixedDepositRepository repository) {
        this.repository = repository;
    }

    /**
     * Create a new Fixed Deposit
     */
    public FixedDeposit createFD(
            String username,
            Double amount,
            Integer tenureMonths,
            Boolean autoRenew) {

        FixedDeposit fd = new FixedDeposit();
        fd.setUsername(username);
        fd.setAmount(amount);
        fd.setTenureMonths(tenureMonths);
        fd.setAutoRenew(autoRenew);

        LocalDate startDate = LocalDate.now();
        fd.setStartDate(startDate);
        fd.setMaturityDate(startDate.plusMonths(tenureMonths));

        fd.setStatus("ACTIVE");

        return repository.save(fd);
    }

    /**
     * Fetch all FDs of a user
     */
    public List<FixedDeposit> getFDsForUser(String username) {
        return repository.findByUsername(username);
    }

    /**
     * Auto-renew matured FDs (called by scheduler)
     */
    @Transactional
    public void processMaturedFDs() {

        List<FixedDeposit> maturedFDs =
                repository.findByMaturityDateLessThanEqualAndStatus(
                        LocalDate.now(), "ACTIVE");

        for (FixedDeposit fd : maturedFDs) {

            if (Boolean.TRUE.equals(fd.getAutoRenew())) {

                LocalDate newStart = LocalDate.now();
                fd.setStartDate(newStart);
                fd.setMaturityDate(
                        newStart.plusMonths(fd.getTenureMonths()));

                // status remains ACTIVE

            } else {
                fd.setStatus("CLOSED");
            }

            repository.save(fd);
        }
    }
}
