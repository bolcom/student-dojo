package com.bol.emailservice.resource;

import com.bol.emailservice.api.Email;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class EmailServiceResource {

    @PUT
    @Path("email")
    @Timed
    public void sendEmailFor(Email email) {
        throw new WebApplicationException(Response.Status.ACCEPTED);
    }
}
