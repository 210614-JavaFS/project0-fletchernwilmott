package com.revature.controllers;

import java.util.List;
import java.util.Scanner;


import com.revature.models.Account;
import com.revature.services.AccountService;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class AccountController {

	private static AccountService accountService = new AccountService();
	private static Scanner sc = new Scanner(System.in);
	
	public void createAccount() {
		System.out.println("Welcome to the account creator");
		
		System.out.println("First, please tell us what you want your account number to be");
		int accountNumber = sc.nextInt();
		sc.nextLine();
		
		System.out.println("Please input the username under which you want this account created\n"
				+ "this must match a username in our database of registered users");
		String username = sc.nextLine();
		
		System.out.println("Please input your desired password");
		String password = sc.nextLine();
		
		String accessType = "";
		
		accessTypeLoop:
		while(true) {
			System.out.println("Tell us whether this account is going to be a customer, employee, or admin account. (C/E/A)");
			accessType = sc.nextLine().toUpperCase();
		
			// confirm the account type char
			String temp = "";
			switch (accessType) {
				case "C":
					temp = "Customer";
					break accessTypeLoop;
				case "E":
					temp = "Employee";
					break accessTypeLoop;
				case "A":
					temp = "Admin";
					break accessTypeLoop;
				default:
					// if you enter a wrong account type, go back to the top
					System.out.println("That isn't an acceptabe access type, try again");
					continue accessTypeLoop;
			}
		}
		
		System.out.println("Next, please enter whether this will be a checkings or savings account (C/S).");
		String accountType = sc.nextLine().toUpperCase();
		
		if(!accountType.equals("C") && !accountType.equals("S")){
			System.out.println("Your account type is incorrect, try again");
			createAccount();
		}

		Account account = new Account(accountNumber, username, password, accessType, accountType);
		
		if(accountService.addAccount(account)) {
			System.out.println("Your account was added to the database");
		}
		else {
			System.out.println("Something went wrong, account not added");
		}
	}
}
