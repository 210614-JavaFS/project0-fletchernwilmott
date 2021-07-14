package com.revature.controllers;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.Person;
import com.revature.services.LoginService;

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
		
		String loggedIn = loginService.loginCheck(accountNumResponse, usernameResponse, passwordResponse);
		
		if(loggedIn.equals("C")) {
			customerMenu(accountNumResponse);
			return;
		}
		else if(loggedIn.equals("E")) {
			employeeMenu(accountNumResponse);
			return;
		}
		else if(loggedIn.equals("A")) {
			adminMenu(accountNumResponse);
			return;
		}
		else {
			System.out.println("No access type was returned, failure");
		}
		
	}
	
	public void customerMenu(int accountNum) {
		
		customerMenuLoop:
		while(true) {
			System.out.println("Welcome back customer, here are your options\n"
					+ "1. Make a deposit\n"
					+ "2. Make a withdrawl\n"
					+ "3. Make a transfer\n"
					+ "4. Exit to the homepage");
			
			String response = sc.nextLine();
			
			switch(response) {
			case "1":
				System.out.println("Please input the amount of money you would like to deposit");
				double deposit = sc.nextDouble();
				sc.nextLine();
				makeDeposit(accountNum, deposit);
				break;
			case "2":
				System.out.println("Please input the amount of money you would like to withdraw");
				double withdrawl = sc.nextDouble();
				sc.nextLine();
				makeWithdrawl(accountNum, withdrawl);
				break;
			case "3":
				System.out.println("Please input the account number you want to send the money to");
				int targetAccount = sc.nextInt();
				sc.nextLine();
				System.out.println("Please input the amount of money you would like to transfer");
				double transfer = sc.nextDouble();
				sc.nextLine();
				makeTransfer(accountNum, targetAccount, transfer);
				break;
			case "4":
				System.out.println("Logging out, returning to homepage");
				return;
			default:
				System.out.println("Not a valid option, try again");
			}
		}
	}
		
	
	private void makeDeposit(int accountNum, double deposit) {
		loginService.getDeposit(accountNum, deposit);
	}
	
	private void makeWithdrawl(int accountNum, double withdrawl) {
		loginService.getWithdrawl(accountNum, withdrawl);
	}
	
	private void makeTransfer(int accountNum, int targetAccount, double transfer) {
		loginService.getTransfer(accountNum, targetAccount, transfer);
	}
	
	
	public void employeeMenu(int accountNum) {
		
		employeeMenuLoop:
		while(true) {
			System.out.println("Welcome back employee, here are your options\n"
					+ "1. View a customers account info\n"
					+ "2. View a customers personal info\n"
					+ "3. Approve or deny account applications\n"
					+ "4. Exit to the homepage");
			
			String response = sc.nextLine();
			String targetUsername;
			String applicationResponse;
			int targetAccount;
			
			
			switch(response) {
			case "1":
				System.out.println("Plese input the account number of the account you want to view");
				targetAccount = sc.nextInt();
				sc.nextLine();
				showOneCustomer(targetAccount);
				break;
			case "2":
				System.out.println("Plese input the username of the person you want to view");
				targetUsername = sc.nextLine();
				showOnePerson(targetUsername);
				break;
			case "3":
				showAllApplications();
				System.out.println("Please enter the account number of the account you want to approve or deny");
				try{
					targetAccount = sc.nextInt();
					sc.nextLine();
				}
				catch(InputMismatchException e) {
					continue employeeMenuLoop;
				}
				System.out.println("Next, please enter whether you want to approve or deny this account (A/D)");
				applicationResponse = sc.nextLine().toUpperCase();
				
				if(applicationResponse.equals("A")) {
					Account account = copyAccount(targetAccount);
					pasteAccount(account);
					deleteApplication(targetAccount, applicationResponse);
				}
				else if(applicationResponse.equals("D")) {
					deleteApplication(targetAccount, applicationResponse);
				}
				else {
					System.out.println("Did not enter approve (A) or deny (D), exiting to menu");
					continue employeeMenuLoop;
				}	
				break;
			case "4":
				System.out.println("Logging out, returning to homepage");
				return;
			default:
				System.out.println("Not a valid option, try again");
			}
		}
	}

	
	private Account copyAccount(int targetAccount) {
		return loginService.copyOneAccount(targetAccount);
	}
	
	private void pasteAccount(Account account) {
		loginService.pasteOneAccount(account);
		
	}
	
	private void deleteApplication(int targetAccount, String applicationResponse) {
		loginService.deleteApplication(targetAccount, applicationResponse);
		
	}
	
	private void showOneCustomer(int targetAccount) {
		Account account = loginService.getOneAccount(targetAccount);
		if(account != null) {
			System.out.println(account);
		}
		else {
			System.out.println("Something went wrong, returning to menu");
		}
	}
	
	private void showOnePerson(String targetUsername) {
		Person person = loginService.getOnePerson(targetUsername);
		if(person != null) {
			System.out.println(person);
		}
		else {
			System.out.println("Something went wrong, returning to menu");
		}
	}

	
	public void adminMenu(int accountNum) {
		
		adminMenuLoop:
		while(true) {
			System.out.println("Welcome back admin, here are your options\n"
					+ "1. See all accounts\n"
					+ "2. Make a deposit to someones account\n"
					+ "3. Make a withdrawl from someones account\n"
					+ "4. Make a transfer from someones account\n"
					+ "5. Approve or deny account applications\n"
					+ "6. Cancel someones account\n"
					+ "7. Exit to the homepage");
			
			
			String response = sc.nextLine();
			String applicationResponse;
			int sourceAccount;
			int targetAccount;
			double deposit;
			double withdrawl;
			double transfer;
			
			
			switch(response) {
				case "1":
					showAllAccounts();
					break;
				case "2":
					System.out.println("Please enter the account number of the account you want to deposit to");
					targetAccount = sc.nextInt();
					sc.nextLine();
					
					System.out.println("Please enter the amount of money want to deposit");
					deposit = sc.nextDouble();
					sc.nextLine();
					
					makeDeposit(targetAccount, deposit);
					break;
				case "3":
					System.out.println("Please enter the account number of the account you want to withdraw from");
					targetAccount = sc.nextInt();
					sc.nextLine();
					
					System.out.println("Please input the amount of money you would like to withdraw");
					withdrawl = sc.nextDouble();
					sc.nextLine();
					makeWithdrawl(targetAccount, withdrawl);
					break;
				case "4":
					System.out.println("Please input the account number you want to take the money from");
					sourceAccount = sc.nextInt();
					sc.nextLine();
					
					System.out.println("Please input the account number you want to transfer the money to");
					targetAccount = sc.nextInt();
					sc.nextLine();
					
					System.out.println("Please input the amount of money you would like to transfer");
					transfer = sc.nextDouble();
					sc.nextLine();
					
					makeTransfer(sourceAccount, targetAccount, transfer);
					break;
				case "5":
					showAllApplications();
					System.out.println("Please enter the account number of the account you want to approve or deny");
					try{
						targetAccount = sc.nextInt();
						sc.nextLine();
					}
					catch(InputMismatchException e) {
						continue adminMenuLoop;
					}
					System.out.println("Next, please enter whether you want to approve or deny this account (A/D)");
					applicationResponse = sc.nextLine().toUpperCase();
					
					if(applicationResponse.equals("A")) {
						Account account = copyAccount(targetAccount);
						pasteAccount(account);
						deleteApplication(targetAccount, applicationResponse);
					}
					else if(applicationResponse.equals("D")) {
						deleteApplication(targetAccount, applicationResponse);
					}
					else {
						System.out.println("Did not enter approve (A) or deny (D), exiting to menu");
						continue adminMenuLoop;
					}	
					break;
				case "6":
					System.out.println("Please enter the account number of the account you want to delete");
					targetAccount = sc.nextInt();
					sc.nextLine();
					deleteAccount(targetAccount);
					break;
				case "7":
					System.out.println("Logging out, returning to homepage");
					return;
				default:
					System.out.println("Not a valid option, try again");
			}
		}
	}
	
	private void deleteAccount(int targetAccount) {
		loginService.deleteAccount(targetAccount);
	}

	private void showAllAccounts() {
		List<Account> accounts = loginService.getAllAccounts();
		for(Account a:accounts) {
			System.out.println(a);
		}
	}
	private void showAllApplications() {
		List<Account> accounts = loginService.getAllApplications();
		for(Account a:accounts) {
			System.out.println(a);
		}
	}
}
