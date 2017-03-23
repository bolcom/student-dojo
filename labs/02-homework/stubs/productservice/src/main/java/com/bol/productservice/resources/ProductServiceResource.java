package com.bol.productservice.resources;

import com.bol.productservice.api.Product;
import com.bol.productservice.jdbi.ProductDao;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import java.util.List;

@Path("/v1")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class ProductServiceResource {
    private final ProductDao productDao;

    public ProductServiceResource(ProductDao customerDao) {
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
        return productDao.getProduct(ean)
                .orElseThrow(() -> new WebApplicationException(Status.NOT_FOUND));
    }
}
