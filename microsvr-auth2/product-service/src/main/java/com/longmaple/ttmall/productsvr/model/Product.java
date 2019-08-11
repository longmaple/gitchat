package com.longmaple.ttmall.productsvr.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@Column(name = "product_id", nullable = false)
	private String productId;

	@Column(name = "vendor_id", nullable = false)
	private String vendorId;

	@Column(name = "product_name", nullable = false)
	private String productName;

	@Column(name = "category", nullable = false)
	private String category;

	@Column(name = "in_stock", nullable = false)
	private Integer inStock;

	@Column(name = "price", nullable = false)
	private BigDecimal price;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getInStock() {
		return inStock;
	}

	public void setInStock(Integer inStock) {
		this.inStock = inStock;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
