package com.bol.productservice;

import com.bol.productservice.api.dao.ProductDao;
import com.bol.productservice.health.V1ApiHealthCheck;
import com.bol.productservice.resource.CustomerServiceResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ProductServiceApplication extends Application<ProductServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new ProductServiceApplication().run(args);

    }

    @Override
    public String getName() {
        return "product-service";
    }

    @Override
    public void initialize(Bootstrap<ProductServiceConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(ProductServiceConfiguration configuration, Environment environment) throws Exception {

        ProductDao customerDao = new ProductDao();
        environment.jersey().register(new CustomerServiceResource(customerDao));
        environment.healthChecks().register("api.v1", new V1ApiHealthCheck());
    }
}
