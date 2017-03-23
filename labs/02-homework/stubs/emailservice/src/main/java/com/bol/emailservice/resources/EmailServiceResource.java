package com.bol.emailservice.resources;

import com.bol.emailservice.api.Email;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class EmailServiceResource {
    private final Logger logger = LoggerFactory.getLogger(EmailServiceResource.class);

    @PUT
    @Path("email")
    @Timed
    public void sendEmail(Email email) {
        logger.info("Sent email to [{}] with subject [{}] and body [{}]",
                email.getEmailAddress(),
                email.getSubject(),
                email.getBody()
        );
        throw new WebApplicationException(Response.Status.ACCEPTED);
    }
}
