package com.bol.customerservice.resources;

import com.bol.customerservice.api.Customer;
import com.bol.customerservice.jdbi.CustomerDao;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import java.util.List;

@Path("/v1")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class CustomerServiceResource {
    private final CustomerDao customerDao;

    public CustomerServiceResource(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @GET
    @Path("customer")
    @Timed
    public List<Customer> getCustomers() {
        return customerDao.getCustomers();
    }

    @GET
    @Path("customer/{customerNumber}")
    @Timed
    public Customer getCustomer(@PathParam("customerNumber") Long customerNumber) {
        return customerDao.getCustomer(customerNumber)
                .orElseThrow(() -> new WebApplicationException(Status.NOT_FOUND));
    }
}
