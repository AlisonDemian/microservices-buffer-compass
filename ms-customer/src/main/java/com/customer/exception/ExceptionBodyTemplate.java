package com.customer.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ExceptionBodyTemplate {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cause;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String parameter;

    public ExceptionBodyTemplate(String message, String cause, String parameter) {
        this.message = message;
        this.cause = cause;
        this.parameter = parameter;
    }

    public ExceptionBodyTemplate(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}