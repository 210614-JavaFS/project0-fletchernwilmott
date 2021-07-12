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
	
	public void accountMenu() {
		System.out.println("what would you like to do with your accounts?\n"
				+ "1) see all accounts\n"
				+ "2) see one account\n"
				+ "3) add a new account\n"
				+ "4) return to previous menu");
		
		String response = sc.nextLine();
		
		switch(response){
			
			case "1":
				showAllAccounts();
				break;
			case "2":
				showOneAccount();
				break;
			case"3":
				addAccount();
				break;
			default:
				return;
		}
		
	}
	
	
	private void showAllAccounts() {
		List<Account> accounts = accountService.getAllAccounts();
		for(Account a:accounts) {
			System.out.println(a);
		}
	}
	
	private void showOneAccount() {
		System.out.println("What is the username of the account you want to see?");
		String response = sc.nextLine();
		Account account = accountService.getAccount(response);
		
		if(account != null) {
			System.out.println(account);
		}
		else {
			System.out.println("Username not found, try again");
			showOneAccount();
		}
		
	}
	
	private void addAccount() {
		System.out.println("Please input the username under which you want this account created,"
				+ " this username must be matched with one in our database of people");
		String username = sc.nextLine();
		
		System.out.println("Please input your desired password");
		String password = sc.nextLine();
		
		System.out.println("Tell us whether this account is going to be a customer, employee, or admin account. (C/E/A)");
		String accessType = sc.nextLine().toUpperCase();
		
		accessTypeLoop:
		while(true) {
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
		
		Account account = new Account(username, password, accessType);
		
		if(accountService.addAccount(account)) {
			System.out.println("Your account was added to the database");
		}
		else {
			System.out.println("Something went wrong, account not added");
		}
		
	}
	
	
}
