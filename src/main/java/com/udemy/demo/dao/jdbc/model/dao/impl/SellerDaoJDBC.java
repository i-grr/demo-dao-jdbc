package com.udemy.demo.dao.jdbc.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.udemy.demo.dao.jdbc.db.DB;
import com.udemy.demo.dao.jdbc.db.DbException;
import com.udemy.demo.dao.jdbc.model.dao.SellerDao;
import com.udemy.demo.dao.jdbc.model.entities.Department;
import com.udemy.demo.dao.jdbc.model.entities.Seller;
import com.udemy.demo.dao.jdbc.model.service.SellerRule;

public class SellerDaoJDBC implements SellerDao {

	public void insert(Seller obj) {
		
		PreparedStatement preparedStatement = null;
		
		try (Connection conn = DB.getConnection()) {
			
			String sql = "INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) "
					   + "VALUES (?, ?, ?, ?, ?)";
			preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, obj.getName());
			preparedStatement.setString(2, obj.getEmail());
			preparedStatement.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			preparedStatement.setDouble(4, obj.getBaseSalary());
			preparedStatement.setInt(5, obj.getDepartment().getId());
			
			int rowsAffected = preparedStatement.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					int id = resultSet.getInt(1);
					obj.setId(id);
				}
			}
				else {
					throw new DbException("Unexpected error! No rows affected!");
				}
			
		}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
				finally {
					DB.closeStatement(preparedStatement);
				}
		
	}

	public void update(Seller obj) {
		
		PreparedStatement preparedStatement = null;
		
		try (Connection conn = DB.getConnection()) {
			
			String sql = "UPDATE seller "
					   + "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
					   + "WHERE Id = ?";
			preparedStatement = conn.prepareStatement(sql);
			
			preparedStatement.setString(1, obj.getName());
			preparedStatement.setString(2, obj.getEmail());
			preparedStatement.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			preparedStatement.setDouble(4, obj.getBaseSalary());
			preparedStatement.setInt(5, obj.getDepartment().getId());
			preparedStatement.setInt(6, obj.getId());
			
			preparedStatement.executeUpdate();
			
		}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
				finally {
					DB.closeStatement(preparedStatement);
				}
		
	}

	public void deleteById(Integer id) {
		
		PreparedStatement preparedStatement = null;
		
		try (Connection conn = DB.getConnection()) {
			
			String sql = "DELETE FROM seller WHERE Id = ?";
			preparedStatement = conn.prepareStatement(sql);
			
			preparedStatement.setInt(1, id);
			
			int rowsAffected = preparedStatement.executeUpdate();
			
			if (rowsAffected == 0) {
				throw new DbException("The inserted id does not exist");
			}
			
		}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
				finally {
					DB.closeStatement(preparedStatement);
				}
		
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
				Department department = new SellerRule().instantiateDepartment(resultSet);
				Seller obj = new SellerRule().instantiateSeller(resultSet, department);
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

	public List<Seller> findAll() {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try (Connection conn = DB.getConnection()) {
			
			String sql = "SELECT seller.*, department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "ORDER BY Name";
			preparedStatement = conn.prepareStatement(sql);
			
			resultSet = preparedStatement.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			
			while (resultSet.next()) {
				
				Department dep = map.get(resultSet.getInt("DepartmentId"));
				
				if (dep == null) {
					dep = new SellerRule().instantiateDepartment(resultSet);
					map.put(resultSet.getInt("DepartmentId"), dep);
				}

				Seller obj = new SellerRule().instantiateSeller(resultSet, dep);
				list.add(obj);
			}
			
			return list;
			
		}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
				finally {
					DB.closeResultSet(resultSet);
					DB.closeStatement(preparedStatement);
				}
		
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try (Connection conn = DB.getConnection()) {
			
			String sql = "SELECT seller.*, department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name";
			preparedStatement = conn.prepareStatement(sql);
			
			preparedStatement.setInt(1, department.getId());
			resultSet = preparedStatement.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			
			while (resultSet.next()) {
				
				Department dep = map.get(resultSet.getInt("DepartmentId"));
				
				if (dep == null) {
					dep = new SellerRule().instantiateDepartment(resultSet);
					map.put(resultSet.getInt("DepartmentId"), dep);
				}

				Seller obj = new SellerRule().instantiateSeller(resultSet, dep);
				list.add(obj);
			}
			
			return list;
			
		}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
				finally {
					DB.closeResultSet(resultSet);
					DB.closeStatement(preparedStatement);
				}
		
	}

}
