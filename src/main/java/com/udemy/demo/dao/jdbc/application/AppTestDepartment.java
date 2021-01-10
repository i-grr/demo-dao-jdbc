package com.udemy.demo.dao.jdbc.application;

import java.util.List;
import java.util.Scanner;

import com.udemy.demo.dao.jdbc.model.dao.DaoFactory;
import com.udemy.demo.dao.jdbc.model.dao.DepartmentDao;
import com.udemy.demo.dao.jdbc.model.entities.Department;

public class AppTestDepartment {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("=== TEST 01: department findById ===");
		Department department = departmentDao.findById(2);
		System.out.println(department);
		
		System.out.println("\n=== TEST 02: department findAll ===");
		List<Department> list = departmentDao.findAll();
		for (Department obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n===TEST 03: department insert ===");
		Department newDepartment = new Department(null, "Kitchen");
		departmentDao.insert(newDepartment);
		System.out.println("Inserted! New id = " + newDepartment.getId());
		
		System.out.println("\n=== TEST 04: department update ===");
		department = departmentDao.findById(1);
		department.setName("Cars");
		departmentDao.update(department);
		System.out.println("Updated completed");
		
		System.out.println("\n=== TEST 05: department deleteById ===");
		System.out.print("Enter id for delete test: ");
		int id = input.nextInt();
		departmentDao.deleteById(id);
		System.out.println("Delete completed");
		
		input.close();
		
		
	}
	
}
