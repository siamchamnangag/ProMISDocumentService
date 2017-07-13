package com.scg.document.model;

/**
 * Created by tanatloke on 7/13/2017 AD.
 */
public class DirDTO {

    private int docid;
    private String description;
    private String status;
    private String user;
    private String link;

    public DirDTO() {
    }

    public DirDTO(String link, int docid, String description, String status, String user) {
        this.link = link;
        this.docid = docid;
        this.description = description;
        this.status = status;
        this.user = user;
    }

    public int getDocid() {
        return docid;
    }

    public void setDocid(int docid) {
        this.docid = docid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
