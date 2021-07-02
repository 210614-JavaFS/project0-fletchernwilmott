package com.revature;

import java.util.*;
import com.revature.models.*;

public class Homepage {

	public static void main(String[] args) {

		// create a scanner object
		Scanner sc = new Scanner(System.in);
		
		
		// if we need people to hold the accounts, we need a method to create people objects
		
		
		
		// declare 2 linkedlists, one for people and one for accounts
		
		LinkedList<Person> people = new LinkedList<Person>();
		LinkedList<Account> accounts = new LinkedList<Account>();
		
		
		
		// we want to store our customers account info in a database of some kind
		// customers can only have 1 account so making the key an object should prevent that
		// the value will be their balance
		// change this to a list
		
		
		// we probably want to auto generate an admin account into the system
		
		
		// while loop to keep the user locked in
		while(true) {
			// ask the user for input
			System.out.println("Hello, welcome to the Bank of Fletcher Homepage");
			System.out.println("Would you like to add your name to our database, create an account, login, or exit? (A/C/L/E)");
			String createOrLogin = sc.nextLine().toUpperCase();
			
			// switch statement for what the user wants to do
			switch (createOrLogin) {
				case "A":
					addName(people);
					// this needs to print the actual object not the memory address
					// use the toString() method?
					System.out.println(people);
					break;
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
		
		
	
	public static void addName(LinkedList<Person> people) {
		
		// create scanner
		Scanner sc = new Scanner(System.in);
		
		addNameLoop:
		while (true) {
			System.out.println("You must add your name to our database before you can open an account");
			System.out.println("Please tell us your first name");
			String firstName = sc.nextLine();
			
			System.out.println("Please tell us your last name");
			String lastName = sc.nextLine();
			
			
			String userName = "";
			boolean userNameMatch = true;
			while (userNameMatch == true) {
				// usernames must be unique
				// iterate through the usernames in the database
				// if theres a match then ask for a new one
				// otherwise proceed
				System.out.println("Please tell us your desired username");
				userName = sc.nextLine();
				
				// search database for username match
				userNameMatch = false;
			
			}
			
			// check the linkedList to make sure this userName doesn't exist
			
			System.out.println("Your first name is: " +firstName+
								"\nYour last name is: "+lastName+
								"\nYour username is: " +userName+
								"\nIs this correct? (Y/N)");
			
			// confirm user input
			String confirmAccInfo = sc.nextLine().toUpperCase();
			if (confirmAccInfo.equals("Y")) {
				System.out.println(firstName+ " has been added to the banks registrar");
				people.add(new Person(firstName, lastName, userName));
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
	
	
	
	
	public static void createAccount() {
		
		// create a scanner object
		Scanner sc = new Scanner(System.in);
		
		// label for breaking out
		createAccountLoop:
		while(true) {
			
			
			System.out.println("Please input the user name under which you want this account created");
			String username = sc.nextLine();
			
			// iterate through the database and check if theres a username match
			// if true, proceed
			// if false, ask for a new user name
			
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
