package com.capgemini.go.service;
import java.sql.SQLException;
import com.capgemini.go.exception.ProductNotFoundException;

public interface OrderService {

	public void insertProduct() throws SQLException, ProductNotFoundException;
	public void placeOrder() throws SQLException;
	public void deleteProduct() throws SQLException, ProductNotFoundException;

}
