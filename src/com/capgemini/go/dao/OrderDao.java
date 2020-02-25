package com.capgemini.go.dao;
import java.sql.SQLException;
import java.util.HashMap;

import com.capgemini.go.dto.OrderDTO;
import com.capgemini.go.exception.ProductNotFoundException;

public interface OrderDao {
	boolean DeleteOrderProductMapEntity(String productId) throws ProductNotFoundException, SQLException;
	boolean InsertOrderProductMapEntity(OrderDTO order) throws SQLException;
	public HashMap<String,OrderDTO> getCartDetails() throws SQLException;
	
	//OrderDTO getProductFromCart(String productId) throws ProductNotFoundException;
	//int productCount();
	//double getCartPrice() throws SQLException;
	OrderDTO SearchProduct(String productId) throws SQLException;
}
