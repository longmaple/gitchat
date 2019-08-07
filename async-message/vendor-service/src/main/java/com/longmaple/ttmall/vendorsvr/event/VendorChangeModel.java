package com.longmaple.ttmall.vendorsvr.event;

public class VendorChangeModel {
	
    private String type;
    private String action;
    private String vendorId;

    public  VendorChangeModel(String type, String action, String vendorId) {
        super();
        this.type   = type;
        this.action = action;
        this.vendorId = vendorId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

}