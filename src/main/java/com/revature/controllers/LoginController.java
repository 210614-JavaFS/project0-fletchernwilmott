package com.revature.controllers;

import java.util.List;
import java.util.Scanner;


import com.revature.models.Account;
import com.revature.services.LoginService;


import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class LoginController {

	private static LoginService loginService = new LoginService();
	private static Scanner sc = new Scanner(System.in);
	
	public void login() {
		
		Account account = new Account();
		System.out.println("Welcome to the login menu, please enter your username");
		String usernameResponse = sc.nextLine();
		
		System.out.println("Next, please enter your password");
		String passwordResponse = sc.nextLine();
		
		System.out.println("Lastly, enter your account number");
		int accountNumResponse = sc.nextInt();
		sc.nextLine();
		
		String loggedIn = loginService.loginCheck(accountNumResponse ,usernameResponse, passwordResponse);
		
		//int account_number = loginService.getAccountNumber(usernameResponse, passwordResponse);
		
		if(loggedIn == "c") {
			customerMenu();
		}
		else if(loggedIn == "e") {
			employeeMenu();
		}
		else if(loggedIn.equals("a")) {
			adminMenu();
		}
		else {
			System.out.println("No access type was returned, failure");
		}
		
	}
	
	public void customerMenu() {
		System.out.println("Welcome back customer, here are your options\n"
				+ "1. Make a deposit\n"
				+ "2. Make a withdrawl\n"
				+ "3. Make a transfer");
		
		String response = sc.nextLine();
		
		switch(response) {
		case "1":
			System.out.println("Please input the amount of money you would like to deposit");
			double deposit = sc.nextDouble();
			sc.nextLine();
			showDeposit(deposit);
		}
	}
		
	
	private void showDeposit(Double deposit) {
		loginService.getDeposit(deposit);
	}
		
	
	public void employeeMenu() {
		System.out.println("Welcome back employee, here are your options\n"
				+ "1. View a customers account info\n"
				+ "2. View a customers personal info\n"
				+ "3. Approve or deny account applications");
		
	}
	
	
	public void adminMenu() {
		System.out.println("Welcome back admin, here are your options\n"
				+ "1. See all accounts\n"
				+ "2. Make a deposit to someones account\n"
				+ "3. Make a withdrawl from someones account\n"
				+ "4. Make a transfer from someones account\n"
				+ "5. Approve or deny account applications\n"
				+ "6. Cancel someones account");
		
		String response = sc.nextLine();
		
		switch(response) {
			case "1":
				showAllAccounts();
				break;
			case "2":
				break;
		}
		
	}
	
	private void showAllAccounts() {
		List<Account> accounts = loginService.getAllAccounts();
		for(Account a:accounts) {
			System.out.println(a);
		}
	}
	
	private void showOneAccount() {
		System.out.println("What is the username of the account you want to see?");
		String response = sc.nextLine();
		/*Account account = accountService.getAccount(response);
		
		if(account != null) {
			System.out.println(account);
		}
		else {
			System.out.println("Username not found, try again");
			showOneAccount();
		}
		*/
	}
}
