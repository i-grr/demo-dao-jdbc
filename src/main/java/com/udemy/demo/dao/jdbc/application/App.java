package com.udemy.demo.dao.jdbc.application;

import com.udemy.demo.dao.jdbc.model.entities.Department;

public class App {
	
	public static void main(String[] args) {
		
		Department obj = new Department(1, "Books");
		System.out.println(obj);
		
	}

}
