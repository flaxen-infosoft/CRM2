package com.example.crm;

public class CustomerResponse {
    private String error, message;

    public CustomerResponse(String error, String message) {
        this.error = error;
        this.message = message;
    }

    public CustomerResponse() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
