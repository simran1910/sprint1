package com.capgemini.go.dto;

public class OrderDTO {
	private String productId;
	private String productName;
	private int productQuantity;
	private double price;
	public OrderDTO(String productId, String productName, int productQuantity, double price) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productQuantity = productQuantity;
		this.price = price;
	}
	


	public OrderDTO() {
		// TODO Auto-generated constructor stub
	}



	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "ORDER DETAIL: [productId=" + productId + ", productName=" + productName + ", productQuantity="
				+ productQuantity + ", price=" + price + "]";
	}
	
	

	
}