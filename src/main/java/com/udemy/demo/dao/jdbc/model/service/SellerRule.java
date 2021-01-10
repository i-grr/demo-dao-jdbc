package com.udemy.demo.dao.jdbc.model.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.udemy.demo.dao.jdbc.model.entities.Department;
import com.udemy.demo.dao.jdbc.model.entities.Seller;

public class SellerRule {
	
	public Seller instantiateSeller(ResultSet resultSet, Department department) throws SQLException {
		Seller obj = new Seller();
		
		obj.setId(resultSet.getInt("Id"));
		obj.setName(resultSet.getString("Name"));
		obj.setEmail(resultSet.getString("Email"));
		obj.setBaseSalary(resultSet.getDouble("BaseSalary"));
		obj.setBirthDate(resultSet.getDate("BirthDate"));
		obj.setDepartment(department);

		return obj;
	}
	
	public Department instantiateDepartment(ResultSet resultSet) throws SQLException {
		Department department = new Department();
		
		department.setId(resultSet.getInt("DepartmentId"));
		department.setName(resultSet.getString("DepName"));
		
		return department;	
	}

}
