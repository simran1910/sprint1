package com.capgemini.go.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.capgemini.go.dao.OrderDao;
import com.capgemini.go.dao.OrderDaoImpl;

public class OrderDaoImplTest {

	OrderDao dao;
	@Before
	public void setUp() throws Exception {
	dao=new OrderDaoImpl();
	
	}

	@After
	public void tearDown() throws Exception {
	dao=null;
	}

	@Test
	public void testOrderDaoImpl() {
	}

	@Test
	public void testOrderDaoImplString() {
	}

	@Test
	public void testGetCartDetails() throws SQLException {
		
	 assertEquals(3,dao.getCartDetails().size());
	
	}

	@Test
	public void testSearchProduct() {
	}

	@Test
	public void testInsertOrderProductMapEntity() {
	}

	@Test
	public void testDeleteOrderProductMapEntity() {
	}

}
