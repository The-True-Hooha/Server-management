package com.github.TheTrueHooha.Server.management.Model;

//returns a response to the user for any request that is received to the server

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@SuperBuilder
@JsonInclude(NON_NULL)


public class APIResponse {

    protected String reasonGiven; //returns the message given after a request has been made successful or not
    protected String feedbackMessage; //gives feedback for any request
    protected LocalDateTime dateTime; //date and time of event
    protected int apiStatusCode; //status code for any request
    protected HttpStatus httpStatus; //the http status for any request

    protected String devMessage; //dev message
    protected Map<?, ?> data; //data given
}
