package com.longmaple.ttmall.productsvr.model;

import java.io.Serializable;

public class Vendor implements Serializable {
	
	private static final long serialVersionUID = 6695890534178169888L;
	String vendorId;
    String name;
    String contactName;
    String contactEmail;
    String contactPhone;


    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String id) {
        this.vendorId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }


}
