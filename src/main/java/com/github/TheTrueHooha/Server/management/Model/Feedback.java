package com.github.TheTrueHooha.Server.management.Model;

//returns a response to the user for any request that is received to the server

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@SuperBuilder
@JsonInclude(NON_NULL)


public class Feedback {

    protected String responseGiven;
    protected String feedbackMessage;
    protected LocalDateTime dateTimeStamp;
    protected int apiStatusCode;
    protected HttpStatus httpStatus;

    protected String devMessage;
    protected Map<?,?> data;
}
