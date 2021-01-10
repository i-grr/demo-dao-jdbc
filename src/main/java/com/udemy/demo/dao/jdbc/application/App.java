package com.udemy.demo.dao.jdbc.application;

import java.util.Date;

import com.udemy.demo.dao.jdbc.model.dao.DaoFactory;
import com.udemy.demo.dao.jdbc.model.dao.SellerDao;
import com.udemy.demo.dao.jdbc.model.entities.Department;
import com.udemy.demo.dao.jdbc.model.entities.Seller;

public class App {
	
	public static void main(String[] args) {
		
		Department obj = new Department(1, "Books");
		System.out.println(obj);
		
		Seller seller = new Seller(21, "Bob", "bob@gmail.com", new Date(), 3000.0, obj);
		System.out.println(seller);
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
	}

}
