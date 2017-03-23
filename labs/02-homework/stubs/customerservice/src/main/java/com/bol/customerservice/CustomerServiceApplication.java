package com.bol.customerservice;

import com.bol.customerservice.api.dao.CustomerDao;
import com.bol.customerservice.health.V1ApiHealthCheck;
import com.bol.customerservice.resource.CustomerServiceResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class CustomerServiceApplication extends Application<CustomerServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new CustomerServiceApplication().run(args);

    }

    @Override
    public String getName() {
        return "customer-service";
    }

    @Override
    public void initialize(Bootstrap<CustomerServiceConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(CustomerServiceConfiguration configuration, Environment environment) throws Exception {

        CustomerDao customerDao = new CustomerDao();
        environment.jersey().register(new CustomerServiceResource(customerDao));
        environment.healthChecks().register("api.v1", new V1ApiHealthCheck());
    }
}
