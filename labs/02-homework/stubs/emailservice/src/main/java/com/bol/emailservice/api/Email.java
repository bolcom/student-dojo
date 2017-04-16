package com.bol.emailservice.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Email {
    private String emailAddress, subject, body;

    @JsonCreator
    public Email(
            @JsonProperty("emailAddress") String emailAddress,
            @JsonProperty("subject") String subject,
            @JsonProperty("body") String body) {
        this.emailAddress = emailAddress;
        this.subject = subject;
        this.body = body;
    }

    public Email() {
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }
}
