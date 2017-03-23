package com.bol.productservice.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import com.bol.productservice.api.Product;
import com.bol.productservice.api.dao.ProductDao;
import com.codahale.metrics.annotation.Timed;

@Path("/v1")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class CustomerServiceResource {

    private final ProductDao productDao;

    public CustomerServiceResource(ProductDao customerDao) {

        this.productDao = customerDao;
    }

    @GET
    @Path("product")
    @Timed
    public List<Product> getProducts() {
        return productDao.getProducts();
    }

    @GET
    @Path("product/{ean}")
    @Timed
    public Product getProduct(@PathParam("ean") String ean) {
        return productDao.getProduct(ean).orElseThrow(() -> new WebApplicationException(Status.NOT_FOUND));
    }
}
