package com.capgemini.go.dao;

import java.sql.*;
import java.util.*;

import com.capgemini.go.dto.OrderDTO;
import com.capgemini.go.exception.ProductNotFoundException;
import com.capgemini.go.util.DatabaseUtil;

public class OrderDaoImpl implements OrderDao {
	Connection connection;
	Statement st;
	ResultSet rs;
	PreparedStatement pst;

	public String uid;
	OrderDTO order;
	HashMap<String, OrderDTO> hmap;

	public OrderDaoImpl() throws SQLException {
		connection = DatabaseUtil.myconnection();
	}

	public OrderDaoImpl(String uid) {
		this.uid = uid;
	}// Order

	@Override
	public HashMap<String, OrderDTO> getCartDetails() throws SQLException {
		st = connection.createStatement();
		rs = st.executeQuery("select * from ordertable");
		hmap = new HashMap<>();

		while (rs.next()) {
			order = null;
			order = new OrderDTO(uid, uid, 0, 0);
			order.setProductId(rs.getString(1));
			order.setProductName(rs.getString(2));
			order.setPrice(rs.getDouble(3));
			order.setProductQuantity(rs.getInt(4));
			hmap.put(rs.getString(1), order);
		}
		return hmap;
	}

	@Override
	public OrderDTO SearchProduct(String productId) throws SQLException {
		pst = connection.prepareStatement("select * from ordertable where productId=?");
		pst.setString(1, productId);
		rs = null;
		rs = pst.executeQuery();
		hmap=new HashMap<String, OrderDTO>();
		if (rs.next()) {
			order = null;
			order = new OrderDTO();
			order.setProductId(rs.getString(1));
			//order.setProductName(rs.getString(2));
			//order.setPrice(rs.getDouble(3));
			//order.setProductQuantity(rs.getInt(4));
			hmap.put(rs.getString(1), order);
		}
		return order;
	}
	
	String ch;
	Scanner sc=new Scanner(System.in);

	@Override
	public boolean InsertOrderProductMapEntity(OrderDTO order1) throws SQLException {
		order = SearchProduct(order1.getProductId());
		if (order == null) {
			pst = null;
			pst = connection.prepareStatement("insert into ordertable values(?,?,?,?)");
			pst.setString(1, order1.getProductId());
			pst.setString(2, order1.getProductName());
			pst.setDouble(3, order1.getPrice());
			pst.setInt(4, order1.getProductQuantity());
			int res = pst.executeUpdate();
			if (res == 1) {
			return true;
			} else
				return false;
		}
		return true;
	}// addProduct

	@Override
	public boolean DeleteOrderProductMapEntity(String productId) throws ProductNotFoundException, SQLException {
		order = SearchProduct(productId);
		if (order != null) {
			pst = null;
			pst = connection.prepareStatement("delete from ordertable where productId=?");
			pst.setString(1, productId);
				int res = pst.executeUpdate();
			if (res == 1) {
				System.out.println("Do you want to delete? y/n");
				ch=sc.next();
			if(ch.equalsIgnoreCase("y"))                
			connection.commit();
			else
				connection.rollback();
			return true;
			} else
				return false;
		} else
			throw new ProductNotFoundException("Product with ID " + productId + " is not Found.");
	}

	/*public OrderDTO getProductFromCart(String productId) throws ProductNotFoundException {
		if (hmap.containsKey(productId)) {
			return hmap.get(productId);
		} else {
			throw new ProductNotFoundException("Product with ID " + productId + " is not Found.");
		}
	}

	/*public int productCount() {
		return hmap.size();
	}

	/*
	 * public double getCartPrice() { double price = 0.0d; Iterator<OrderDTO>
	 * iterator = getCartDetails().iterator(); while(iterator.hasNext()){ price+=
	 * iterator.next().getPrice(); } return price; }
	 */
}
