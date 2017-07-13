package com.scg.document.model;

/**
 * Created by marcus on 7/13/2017 AD.
 */
public class GetFileDTO {

    private byte[] body;


    public GetFileDTO() {
    }

    public GetFileDTO(byte[] body) {
        this.body = body;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}
