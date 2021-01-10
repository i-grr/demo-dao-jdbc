package com.udemy.demo.dao.jdbc.application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.udemy.demo.dao.jdbc.model.dao.DaoFactory;
import com.udemy.demo.dao.jdbc.model.dao.SellerDao;
import com.udemy.demo.dao.jdbc.model.entities.Department;
import com.udemy.demo.dao.jdbc.model.entities.Seller;

public class AppTestSeller {
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== TEST 01: seller findById ===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("\n=== TEST 02: seller findByDepartment ===");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for (Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== TEST 03: seller findAll ===");
		list = sellerDao.findAll();
		for (Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== TEST 04: seller insert ===");
		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
		sellerDao.insert(newSeller);
		System.out.println("Inserted! New id = " + newSeller.getId());
		
		System.out.println("\n=== TEST 05: seller update ===");
		seller = sellerDao.findById(1);
		seller.setName("Martha Wayne");
		sellerDao.update(seller);
		System.out.println("Updated completed");
		
		System.out.println("\n=== TEST 06: seller delete ===");
		System.out.print("Enter id for delete test: ");
		int id = input.nextInt();
		sellerDao.deleteById(id);
		System.out.println("Delete completed");
		
		input.close();
		
	}

}
