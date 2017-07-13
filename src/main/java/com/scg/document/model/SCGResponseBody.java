package com.scg.document.model;

/**
 * Created by marcus on 7/13/2017 AD.
 */
public class SCGResponseBody {

    private String message;

    public SCGResponseBody(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
