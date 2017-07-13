package com.scg.document.model;

/**
 * Created by tanatloke on 7/12/2017 AD.
 */
public class UploadFileBody {

    private String filename;

    private byte[] content;

    public UploadFileBody() {
    }

    public UploadFileBody(String filename, byte[] content) {
        this.filename = filename;
        this.content = content;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
