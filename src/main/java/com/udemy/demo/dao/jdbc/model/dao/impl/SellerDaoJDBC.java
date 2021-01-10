package com.udemy.demo.dao.jdbc.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.udemy.demo.dao.jdbc.db.DB;
import com.udemy.demo.dao.jdbc.db.DbException;
import com.udemy.demo.dao.jdbc.model.dao.SellerDao;
import com.udemy.demo.dao.jdbc.model.entities.Department;
import com.udemy.demo.dao.jdbc.model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	public Seller findById(Integer id) {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try (Connection conn = DB.getConnection()) {
			
			String sql = "SELECT seller.*,department.Name as DepName "
					   + "FROM seller INNER JOIN department "
					   + "ON seller.DepartmentId = department.Id "
					   + "WHERE seller.Id = ?";
			preparedStatement = conn.prepareStatement(sql);
			
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				Department department = instantiateDepartment(resultSet);
				Seller obj = instantiateSeller(resultSet, department);
				return obj;
			}
			
			return null;
			
		}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
				finally {
					DB.closeResultSet(resultSet);
					DB.closeStatement(preparedStatement);
				}
		
	}

	private Seller instantiateSeller(ResultSet resultSet, Department department) throws SQLException {
		Seller obj = new Seller();
		
		obj.setId(resultSet.getInt("Id"));
		obj.setName(resultSet.getString("Name"));
		obj.setName(resultSet.getString("Email"));
		obj.setBaseSalary(resultSet.getDouble("BaseSalary"));
		obj.setBirthDate(resultSet.getDate("BirthDate"));
		obj.setDepartment(department);
		
		return obj;
	}

	private Department instantiateDepartment(ResultSet resultSet) throws SQLException {
		Department department = new Department();
		
		department.setId(resultSet.getInt("DepartmentId"));
		department.setName(resultSet.getString("DepName"));
		
		return department;	
	}

	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
