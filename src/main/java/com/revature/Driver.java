package com.revature;


import java.sql.SQLException;
import java.util.Scanner;

import com.revature.models.*;
import com.revature.controllers.AccountController;
import com.revature.controllers.LoginController;

public class Driver {

	private static AccountController accountController = new AccountController();
	private static LoginController loginController = new LoginController();
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args)  {
		System.out.println("Welcome to the Bank of Fletcher");
		
		while(true) {
			System.out.println("This is the homepage\nWould you like to add your name to our database, create an account, log in, or exit? (A/C/L/E)");
			String response = sc.nextLine().toUpperCase();
			
			switch(response) {
				case "A":
					break;
				case "C":
					accountController.createAccount();
					break;
				case "L":
					loginController.login();
					break;
				case "E":
					System.out.println("Now exiting");
					System.exit(0);
			}
		}

	}

}
