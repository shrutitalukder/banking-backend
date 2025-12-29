package com.bankapp.fd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FixedDepositScheduler {

    private static final Logger logger =
            LoggerFactory.getLogger(FixedDepositScheduler.class);

    private final FixedDepositService fdService;

    public FixedDepositScheduler(FixedDepositService fdService) {
        this.fdService = fdService;
    }

    /**
     * Runs every 1 minute (demo purpose)
     * In production → daily once
     */
    @Scheduled(fixedRate = 60000)
    public void autoRenewFDs() {

        logger.info("FD Auto-Renew Scheduler started");

        fdService.processMaturedFDs();

        logger.info("FD Auto-Renew Scheduler completed");
    }
}
