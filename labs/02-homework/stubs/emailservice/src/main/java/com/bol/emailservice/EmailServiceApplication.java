package com.bol.emailservice;

import com.bol.emailservice.health.V1ApiHealthCheck;
import com.bol.emailservice.resource.EmailServiceResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class EmailServiceApplication extends Application<EmailServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new EmailServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<EmailServiceConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(EmailServiceConfiguration configuration, Environment environment) throws Exception {
        environment.jersey().register(new EmailServiceResource());

        environment.healthChecks().register("api.v1", new V1ApiHealthCheck());
    }
}
