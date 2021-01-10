package com.udemy.demo.dao.jdbc.model.dao;

import java.util.List;

import com.udemy.demo.dao.jdbc.model.entities.Seller;

public interface SellerDao {

	void insert(Seller obj);
	
	void update(Seller obj);
	
	void deleteById(Integer id);
	
	Seller findById(Integer id);
	
	List<Seller> findAll();
	
}