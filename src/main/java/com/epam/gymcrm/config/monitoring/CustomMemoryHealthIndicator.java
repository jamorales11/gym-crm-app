package com.epam.gymcrm.config.monitoring;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class CustomMemoryHealthIndicator implements HealthIndicator {


    @Override
    public Health getHealth(boolean includeDetails) {
        return HealthIndicator.super.getHealth(includeDetails);
    }

    @Override
    public Health health() {

        long freeMemory = Runtime.getRuntime().freeMemory();
        long totalMemory = Runtime.getRuntime().totalMemory();

        double freeMemoryPercent = ( (double) freeMemory/ (double) totalMemory) * 100;

        if(freeMemoryPercent > 25 ){
            return Health.up()
                    .withDetail("free_memory", (freeMemory / (1024 * 1024)) + " MB")
                    .withDetail("total_memory", (totalMemory / (1024 * 1024)) + " MB")
                    .withDetail("free_memory_percent", Math.floor(freeMemoryPercent) + " %")
                    .build();
        } else {
            return Health.down()
                    .withDetail("free_memory", (freeMemory / (1024 * 1024)) + " MB")
                    .withDetail("total_memory", (totalMemory / (1024 * 1024)) + " MB")
                    .withDetail("free_memory_percent", Math.floor(freeMemoryPercent) + " %")
                    .build();
        }



    }
}
