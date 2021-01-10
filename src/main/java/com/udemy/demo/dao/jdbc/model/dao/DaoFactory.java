package com.udemy.demo.dao.jdbc.model.dao;

import com.udemy.demo.dao.jdbc.model.dao.impl.DepartmentDaoJDBC;
import com.udemy.demo.dao.jdbc.model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	
	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC();
	}
	
	public static DepartmentDao createDepartmentDao() {
		return new DepartmentDaoJDBC();
	}

}
