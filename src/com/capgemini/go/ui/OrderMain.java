package com.capgemini.go.ui;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import com.capgemini.go.dao.OrderDao;
import com.capgemini.go.dao.OrderDaoImpl;
import com.capgemini.go.dto.OrderDTO;
import com.capgemini.go.exception.ProductNotFoundException;
import com.capgemini.go.service.OrderService;
import com.capgemini.go.service.OrderServiceImpl;

public class OrderMain {
	public static void main(String[] args)  {
		Scanner scanner=new Scanner(System.in);
		String ch="yes";
		OrderService service=null;
		OrderDao orderDao=null;
		OrderDaoImpl orderDaoImpl=null;
		try {
		service=new OrderServiceImpl();
		orderDao=new OrderDaoImpl();
		}
		catch (SQLException e1) {
			System.err.println(e1.getMessage());
		}
		while(ch.equalsIgnoreCase("yes")) {
			System.out.println("Select Operation");
			System.out.println("1.Add Product");
			System.out.println("2.Place Order");
			System.out.println("3.Delete Product");
			
			int op=scanner.nextInt();
			switch(op) {
			case 1:
				try {
					service.insertProduct();
					System.out.println();
					System.out.println("PRODUCT ADDED!!!!");
					System.out.println();
					System.out.println("Type 'Yes->2' if you want to place order!");
					System.out.println("Type 'Yes->3' if you want to delete selected product!!");
				}
				catch(SQLException |ProductNotFoundException e1) //pipe operator " | "
				{
					System.err.println(e1.getMessage());
					//e1.printStackTrace();
				}
				break;
			case 2:
				try {
					service.placeOrder();
					System.out.println();
					System.out.println("Want to place Order? yes/no");
					Scanner scan=new Scanner(System.in);
					String c=scan.next();
					if(c.equalsIgnoreCase("yes"))
					System.out.println("ORDER PLACED!!!!");
					else
						System.out.println("ORDER NOT PLACED!");
				}
				catch(SQLException e) {
					System.err.println(e.getMessage());
				}
				break;
			case 3:
				try {
					service.deleteProduct();;
				}
				catch(SQLException|ProductNotFoundException e) {
					System.err.println(e.getMessage());
					//e.printStackTrace();
				}
				break;
				default:
					break;
			}
			System.out.println("Continue yes\\no");
			ch=scanner.next();
			
		}

}
	}
