package com.bankapp.monitoring;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class BankingHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {

        boolean bankingServicesUp = true;

        if (bankingServicesUp) {
            return Health.up()
                    .withDetail("Banking-Core", "Operational")
                    .withDetail("IMPS", "Available")
                    .withDetail("FD-Scheduler", "Running")
                    .build();
        }

        return Health.down()
                .withDetail("Banking-Core", "Down")
                .build();
    }
}
