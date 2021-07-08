package com.revature;

import java.util.*;
import com.revature.models.*;

public class Homepage {

	public static void main(String[] args) {

		// create a scanner object
		Scanner sc = new Scanner(System.in);
		
		// these array lists are temporary
		// we want to store our customers account info in a database of some kind
		ArrayList<Person> people = new ArrayList<Person>();
		ArrayList<Account> accounts = new ArrayList<Account>();
		
		// this is the homepage while loop
		while(true) {
			// ask the user for input
			System.out.println("Hello, welcome to the Bank of Fletcher Homepage");
			System.out.println("Would you like to add your name to our database, create an account, log in, or exit? (A/C/L/E)");
			String createOrLogin = sc.nextLine().toUpperCase();
			
			// switch statement for what the user wants to do
			switch (createOrLogin) {
				case "A":
					// adds a person to the ArrayList "people"
					addPerson(people);
					break;
				case "C":
					//call create account method
					createAccount(people, accounts);
					break;
				// login case
				case "L":
					// call log in method
					logIn(accounts);
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
		
		
	
	public static void addPerson(ArrayList<Person> people) {
		
		// create scanner
		Scanner sc = new Scanner(System.in);
		
		addNameLoop:
		while (true) {
			System.out.println("You must add your name to our database before you can open an account");
			System.out.println("Please tell us your first name");
			String firstName = sc.nextLine();
			
			System.out.println("Please tell us your last name");
			String lastName = sc.nextLine();
			
			String username = "";
			boolean usernameMatch = true;
			
			usernameMatchLoop:
			while (usernameMatch == true) {
				System.out.println("Please tell us your desired username");
				username = sc.nextLine();
				// check through the ArrayList to see if the username already exists
				for(int i = 0; i < people.size(); i++) {
					if(people.get(i).getUsername().equals(username)) {
						System.out.println("This username already exists, please choose another.");
						continue usernameMatchLoop;
					}
				}
				usernameMatch = false;
			}
			
			
			System.out.println("Your first name is: " +firstName+
								"\nYour last name is: "+lastName+
								"\nYour username is: " +username+
								"\nIs this correct? (Y/N)");
			
			// confirm user input
			String confirmAccInfo = sc.nextLine().toUpperCase();
			if (confirmAccInfo.equals("Y")) {
				System.out.println(firstName+ " has been added to the banks registrar");
				people.add(new Person(firstName, lastName, username));
				break;
			}
			// if the user doesn't like their account info, continue back to a new iteration of the loop
			else if(confirmAccInfo.equals("N")) {
				System.out.println("Ok, try and make your account again");
				continue addNameLoop;
			}
			// if the user enters an invalid option try again
			else {
				System.out.println("Not a valid option, try again");
			}
			
			
			// exit loop check
			while(true) {
				System.out.println("Would you like to add another person or return to the homepage? (A/H)");
				String createOrHome = sc.nextLine().toUpperCase();
				switch (createOrHome) {
				case "A":
					continue addNameLoop;
				case "H":
					return;
				default:
					System.out.println("Not a valid option, try again");
				}
			}

		}// end of addNameLoop
	}// end of addName method
	
	
	public static void createAccount(ArrayList<Person> people, ArrayList<Account> accounts) {
		
		// create a scanner object
		Scanner sc = new Scanner(System.in);
		
		createAccountLoop:
		while(true) {
		
			String username;
			
			userNameMatchLoop:
			while(true) {
				// the username they input must match one in the "people" arraylist
				System.out.println("Please input the username under which you want this account created,"
						+ " this username must be matched with one in our database of people");
				username = sc.nextLine();
				
				for(int i = 0; i < people.size(); i++) {
					if(people.get(i).getUsername().equals(username)) {
						System.out.println("Username found");
						break userNameMatchLoop;
					}
				}
				// if we don't find the username, we ask them to input a new one
				System.out.println("We couldn't find that username in our database of registered people, please try another");
			}
			
			// input password
			System.out.println("Please input your desired password");
			String password = sc.nextLine();
			
			// make sure the user inputs either checking or savings
			System.out.println("Please input whether this is a checking or savings account");
			String accountType = sc.nextLine().toLowerCase();
			switch (accountType) {
				case "checking":
					break;
				case "savings":
					break;
				default:
					System.out.println("That isn't an acceptable account type, try again");
					continue;
			}
			
			// accounts must apply to be created and can be approved or denied by an employee or admin
			System.out.println("Tell us whether this account is going to be a customer, employee, or admin account. (C/E/A)");
			String accessType = sc.nextLine().toUpperCase();
			
			// confirm the account type char
			String temp = "";
			switch (accessType) {
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
					// if you enter a wrong account type, go back to the top
					System.out.println("That isn't an acceptabe access type, try again");
					continue;
			}
			
			// while loop for checking user input
			while(true) {
				
				// print out a confirmation message to the user
				System.out.println("Your username will be: " +username+
									"\nYour password will be: " +password+
									"\nYour account type is: " +accountType+
									"\nYour access type is: " +temp+
									"\nIs this correct? (Y/N)");
				String confirmAccInfo = sc.nextLine().toUpperCase();
			
				// if yes then create a new account and add it to the accounts ArrayList
				if (confirmAccInfo.equals("Y")) {
					accounts.add(new Account(username, password, accountType, accessType));
					System.out.println("Account approval application has been sent to the administrator");
					break;
				}
				// if the user doesn't like their account info, continue back to a new iteration of the loop
				else if(confirmAccInfo.equals("N")) {
					System.out.println("Ok, try and make your account again");
					continue createAccountLoop;
				}
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
	
	
	public static void logIn(ArrayList<Account> accounts) {
		
		Scanner sc = new Scanner(System.in);
		
		int indexOfUsername = 0;
		
		// ask the user for their username and then search the database for it
		userNameSearchLoop:
		while(true) {
			System.out.println("Please input your username");
			String username = sc.nextLine();
			
			for(int i = 0; i < accounts.size(); i++) {
				if(accounts.get(i).getUsername().equals(username)) {
					System.out.println("Username found");
					indexOfUsername = i;
					break userNameSearchLoop;
				}
			}
			System.out.println("Username not found, try another or exit to the homepage? (T/E)");
			String tryOrExit = sc.nextLine();
			switch(tryOrExit) {
				case "T":
					continue userNameSearchLoop;
				
				case "E":
					return;
			}
		}
		
		
		// password match loop
		
		System.out.println("Please input the password");
		String password = sc.nextLine();
		
		// if the password at indexOfUsername equals password
		if(accounts.get(indexOfUsername).getPassword().equals(password)){
			System.out.println("Password accepted");
		}
		
		
		
		
		
	}
	
	
	
}
