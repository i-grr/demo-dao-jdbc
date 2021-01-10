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
import com.udemy.demo.dao.jdbc.model.dao.DepartmentDao;
import com.udemy.demo.dao.jdbc.model.entities.Department;
import com.udemy.demo.dao.jdbc.model.service.DepartmentRule;

public class DepartmentDaoJDBC implements DepartmentDao {

	@Override
	public void insert(Department obj) {
		
		PreparedStatement preparedStatement = null;
		
		try (Connection conn = DB.getConnection()) {
			
			String sql = "INSERT INTO department (Name) VALUES (?)";
			preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, obj.getName());
			
			if (findAll().contains(obj)) {
				System.out.println("The inserted department already exists!");
			}
				else {
					
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
			
		}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
				finally {
					DB.closeStatement(preparedStatement);
				}
	}

	@Override
	public void update(Department obj) {
		
		PreparedStatement preparedStatement = null;
		
		try (Connection conn = DB.getConnection()) {
			
			String sql = "UPDATE department SET name = ? WHERE id = ?";
			preparedStatement = conn.prepareStatement(sql);
			
			preparedStatement.setString(1, obj.getName());
			preparedStatement.setInt(2, obj.getId());
			
			preparedStatement.executeUpdate();
			
		}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
				finally {
					DB.closeStatement(preparedStatement);
				}
	}

	@Override
	public void deleteById(Integer id) {
		
		PreparedStatement preparedStatement = null;
		
		try (Connection conn = DB.getConnection()) {
			
			String sql = "DELETE FROM department WHERE Id = ?";
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

	@Override
	public Department findById(Integer id) {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try (Connection conn = DB.getConnection()) {
			
			String sql = "SELECT * FROM department WHERE id = ?";
			preparedStatement = conn.prepareStatement(sql);
			
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				Department department = new DepartmentRule().instantiateDepartment(resultSet);
				return department;
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

	@Override
	public List<Department> findAll() {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try (Connection conn = DB.getConnection()) {
			
			String sql = "SELECT * FROM department";
			preparedStatement = conn.prepareStatement(sql);
			
			resultSet = preparedStatement.executeQuery();
			
			List<Department> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			
			while (resultSet.next()) {
				
				Department dep = map.get(resultSet.getInt("Id"));
				
				if (dep == null) {
					dep = new DepartmentRule().instantiateDepartment(resultSet);
					map.put(resultSet.getInt("Id"), dep);
				}

				Department obj = new DepartmentRule().instantiateDepartment(resultSet);
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
