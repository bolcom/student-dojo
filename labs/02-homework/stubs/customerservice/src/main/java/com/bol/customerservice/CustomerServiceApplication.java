package com.bol.customerservice;

import com.bol.customerservice.health.V1ApiHealthCheck;
import com.bol.customerservice.jdbi.CustomerDao;
import com.bol.customerservice.resources.CustomerServiceResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class CustomerServiceApplication extends Application<CustomerServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new CustomerServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "customerservice";
    }

    @Override
    public void run(CustomerServiceConfiguration configuration, Environment environment) throws Exception {
        environment.jersey().register(new CustomerServiceResource(new CustomerDao()));
        environment.healthChecks().register("api.v1", new V1ApiHealthCheck());
    }
}
