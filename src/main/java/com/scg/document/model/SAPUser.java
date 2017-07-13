package com.scg.document.model;

/**
 * Created by marcus on 7/13/2017 AD.
 */
public class SAPUser {

    private String lanid;
    private String sapid;
    private String fullname;

    public SAPUser() {
    }

    public SAPUser(String lanid, String sapid, String fullname) {
        this.lanid = lanid;
        this.sapid = sapid;
        this.fullname = fullname;
    }

    public String getLanid() {
        return lanid;
    }

    public void setLanid(String lanid) {
        this.lanid = lanid;
    }

    public String getSapid() {
        return sapid;
    }

    public void setSapid(String sapid) {
        this.sapid = sapid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
