package com.epam.gymcrm.config.monitoring;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class CustomExternalServiceHealthIndicator implements HealthIndicator {

    private final String EXAMPLE_SERVICE = "servicio_ejemplo";

    private final Random random = new Random();
    private final List<Integer> statusCodes = List.of(200, 204, 401, 404, 503);


    @Override
    public Health health() {

        int randomStatusCode = statusCodes.get(random.nextInt(statusCodes.size()));

        Health.Builder healthBuilder = new Health.Builder();

        return (switch(randomStatusCode) {
            case 200, 204 -> healthBuilder.up()
                    .withDetail(EXAMPLE_SERVICE, "Service is Up and Running ✅")
                    .withDetail("url", "https://example.com")
                    .withDetail("status", randomStatusCode);
            case 503 -> healthBuilder.down()
                    .withDetail(EXAMPLE_SERVICE, "Service is Down 🔻")
                    .withDetail("alternative_url", "https://alt-example.com")
                    .withDetail("status", randomStatusCode);
            default -> healthBuilder.unknown().withException(new RuntimeException("Received status: " + randomStatusCode));
        }).build();


    }

}
