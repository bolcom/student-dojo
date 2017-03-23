package com.bol.customerservice.health;

import com.codahale.metrics.health.HealthCheck;

public class V1ApiHealthCheck extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
