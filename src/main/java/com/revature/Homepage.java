package com.revature;

import java.util.*;

public class Homepage {

	public static void main(String[] args) {

		// create a scanner object
		Scanner sc = new Scanner(System.in);
		
		// we want to store our customers account info in a database of some kind
		// customers can only have 1 account so making the key an object should prevent that
		// the value will be their balance
		// change this to a list
		
		
		// we probably want to auto generate an admin account into the system
		
		
		// while loop to keep the user locked in
		while(true) {
			// ask the user for input
			System.out.println("Hello, welcome to the Bank of Fletcher Homepage");
			System.out.println("Would you like to create an account, login, or exit? (C/L/E)");
			String createOrLogin = sc.nextLine().toUpperCase();
			
			// switch statement for what the user wants to do
			switch (createOrLogin) {
				case "C":
					//call create account method
					createAccount();
					break;
				// login case
				case "L":
					// call login class/method
					break;
				// exit case
				case "E":
					System.exit(0);
					
				// if the user enters something wrong
				default:
					System.out.println("Not an option, try again");
					break;
			}// end of create or login switch
			
		}// end of the homepage
	}
		
		
	public static void createAccount() {
		
		// create a scanner object
		Scanner sc = new Scanner(System.in);
		
		// label for breaking out
		createAccountLoop:
		while(true) {
			// usernames must be unique
			System.out.println("Please input your desired username");
			String username = sc.nextLine();
			
			// iterate through the database and check if theres a username match
			// if true, ask for another username
			// if false, go to next line
			
			// input password
			System.out.println("Please input your desired password");
			String password = sc.nextLine();
			
			// accounts must apply to be created and can be approved or denied by an employee or admin
			System.out.println("Tell us whether this account is going to be a customer, employee, or admin account. (C/E/A)");
			String type = sc.nextLine().toUpperCase();
			
			// confirm the account type char
			String temp = "";
			switch (type) {
				case "C":
					temp = "Customer";
					break;
				case "E":
					temp = "Employee";
					break;
				case "A":
					temp = "Admin";
					break;
				default:
					// if you enter a wrong account type, go back to the homepage
					System.out.println("That isn't an acceptable account type, try again");
					continue;
			}
			
			while(true) {
			// print out a confirmation message to the user
			System.out.println("Your username will be: " +username+ "\nYour password will be: " +password+ "\nYour account type is: " +temp+ "\nIs this correct? (Y/N)");
			String confirmAccInfo = sc.nextLine().toUpperCase();
			// while loop for checking user input
				// if the user approves their info, they're account info should be saved and an application for account approval should be generated
				if (confirmAccInfo.equals("Y")) {
					// make the customer object and store it in a list or set then break the loop
					System.out.println("Account approval application has been sent to the administrator");
					break;
				}
				// if the user doesn't like their account info, continue back to a new iteration of the loop
				else if(confirmAccInfo.equals("N")) {
					System.out.println("Ok, try and make your account again");
					continue createAccountLoop;
				}
				// if the user enters an invalid option try again
				else {
					System.out.println("Not a valid option, try again");
				}
			}
			
			// exit loop check
			while(true) {
			System.out.println("Would you like to create another account or return to the homepage? (C/H)");
			String createOrHome = sc.nextLine().toUpperCase();
				switch (createOrHome) {
				case "C":
					continue createAccountLoop;
				case "H":
					return;
				default:
					System.out.println("Not a valid option, try again");
				}
			}
		}// end of outer while loop
	}// end of createAccount method
	
	
}
