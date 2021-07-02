package com.revature.models;

public class Account {

	private String username;
	private String password;
	private String accountType;
	private String accessType;
	private double balance;
	
	public Account (String username, String password, String accountType, String accessType) {
		
		this.username = username;
		this.password = password;
		this.accountType = accountType;
		this.accessType = accessType;
		balance = 0;
		
	}
	
	
	
	
}