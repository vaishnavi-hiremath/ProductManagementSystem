package com.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;
	private String productName;
	private String productCategory;
	private int productPrice;
	public int getProductId() {
		return productId;
	}
	public Product setProductId(int productId) {
		this.productId = productId;
		return this;
	}
	public String getProductName() {
		return productName;
	}
	public Product setProductName(String productName) {
		this.productName = productName;
		return this;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public Product setProductCategory(String productCategory) {
		this.productCategory = productCategory;
		return this;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public Product setProductPrice(int productPrice) {
		this.productPrice = productPrice;
		return this;
	}
	
}
