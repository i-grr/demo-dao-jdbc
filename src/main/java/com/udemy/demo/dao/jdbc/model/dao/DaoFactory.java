package com.udemy.demo.dao.jdbc.model.dao;

import com.udemy.demo.dao.jdbc.model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	
	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC();
	}

}
