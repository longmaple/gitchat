package com.longmaple.ttmall.productsvr.model;

import java.util.List;

public class ProductInfo {
	
	private List<Product> products;
	private String vendorName;
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
}
