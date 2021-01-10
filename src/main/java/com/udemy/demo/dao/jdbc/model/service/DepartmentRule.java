package com.udemy.demo.dao.jdbc.model.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.udemy.demo.dao.jdbc.model.entities.Department;

public class DepartmentRule {

	public Department instantiateDepartment(ResultSet resultSet) throws SQLException {
		Department department = new Department();
		
		department.setId(resultSet.getInt("Id"));
		department.setName(resultSet.getString("Name"));
		
		return department;	
	}
	
}
