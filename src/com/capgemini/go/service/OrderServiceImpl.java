package com.capgemini.go.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import com.capgemini.go.dao.OrderDao;
import com.capgemini.go.dao.OrderDaoImpl;
import com.capgemini.go.dto.OrderDTO;
import com.capgemini.go.exception.ProductNotFoundException;

public class OrderServiceImpl implements OrderService {
	OrderDao orderDao;
	HashMap<String,OrderDTO> hashMap;
	OrderDTO order=null;
	Scanner scanner=new Scanner(System.in);
	String productId,productName;
	int productQuantity;
	double price;
	
	public OrderServiceImpl() throws SQLException {
		orderDao=new OrderDaoImpl();
	}
	
	@Override
	public void placeOrder() throws SQLException {
		hashMap=orderDao.getCartDetails();
		Set<String> keyset=hashMap.keySet();
		for(String in:keyset) {
			System.out.println(hashMap.get(in));
		}
	}
	
	@Override
	public void insertProduct() throws SQLException, ProductNotFoundException {
		System.out.println("Add Product Data");
		System.out.println("Enter ProductId");
		order=null;
		order=new OrderDTO(productId, productId, productQuantity, price);
		
		productId=scanner.next();
		if(productId.isEmpty())
			throw new ProductNotFoundException("productId cannot be empty");
		else
		order.setProductId(productId);
		
		System.out.println("Enter productName of 2 or more char");
		scanner.nextLine();
		productName=scanner.nextLine();
		if(productName.isEmpty())
			throw new ProductNotFoundException("productName cannot be empty");
		if(productName.length()<2)
			throw new ProductNotFoundException("productName length cannot be less than 5 char");
		else
		order.setProductName(productName);
		
		System.out.println("Enter price");
		price=scanner.nextDouble();
		if(price<=0)
			throw new ProductNotFoundException("Invalid price <=0");
		else
		order.setPrice(price);
		
		System.out.println("Enter productQuantity");
		productQuantity=scanner.nextInt();
		if(productQuantity<=0)
			throw new ProductNotFoundException("Invalid productQuantity <=0");
	    else
		order.setProductQuantity(productQuantity);
		
		
		if(orderDao.InsertOrderProductMapEntity(order))
			System.out.println("Product Added");
		else
		System.out.println("Product Already Present");
		
		
	}

	@Override
	public void deleteProduct() throws SQLException, ProductNotFoundException {
		System.out.println("Enter the productId of that product you want to delete");
		 
		
		productId=scanner.next();
		if(productId.isEmpty())
			throw new ProductNotFoundException("productId cannot be empty");
		
		else	if(orderDao.DeleteOrderProductMapEntity(productId))
					System.out.println("PRODUCT WITH PRODUCTID "+productId+" IS DELETED");
				else
					System.out.println("PRODUCT NOT PRESENT");

	}

	
}
