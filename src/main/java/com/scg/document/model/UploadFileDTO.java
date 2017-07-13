package com.scg.document.model;

/**
 * Created by tanatloke on 7/13/2017 AD.
 */
public class UploadFileDTO {

    private String link;
    private String message;


    public UploadFileDTO(String link, String message) {
        this.link = link;
        this.message = message;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
