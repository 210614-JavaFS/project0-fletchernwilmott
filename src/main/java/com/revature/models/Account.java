package com.revature.models;

public class Account {

	// this class serves as the basis for all accounts, all accounts should extend this class
	
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
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
}