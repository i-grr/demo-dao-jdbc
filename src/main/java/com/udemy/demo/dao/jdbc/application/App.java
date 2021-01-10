package com.udemy.demo.dao.jdbc.application;

import com.udemy.demo.dao.jdbc.model.dao.DaoFactory;
import com.udemy.demo.dao.jdbc.model.dao.SellerDao;
import com.udemy.demo.dao.jdbc.model.entities.Seller;

public class App {
	
	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== TEST 01: seller findById ===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
	}

}
