package com.bol.productservice;

import com.bol.productservice.health.V1ApiHealthCheck;
import com.bol.productservice.jdbi.ProductDao;
import com.bol.productservice.resources.ProductServiceResource;
import io.dropwizard.Application;
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
    public void run(ProductServiceConfiguration configuration, Environment environment) throws Exception {
        ProductDao customerDao = new ProductDao();
        environment.jersey().register(new ProductServiceResource(customerDao));
        environment.healthChecks().register("api.v1", new V1ApiHealthCheck());
    }
}
