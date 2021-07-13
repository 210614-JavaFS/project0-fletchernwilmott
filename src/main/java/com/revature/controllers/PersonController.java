package com.revature.controllers;

import java.util.List;
import java.util.Scanner;


import com.revature.models.Person;
import com.revature.services.LoginService;
import com.revature.services.PersonService;


import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class PersonController {

	private static PersonService personService = new PersonService();
	private static Scanner sc = new Scanner(System.in);
	
	public void createPerson() {
		System.out.println("Welcome to the person creator");
		
		System.out.println("First, please tell us your first name");
		String firstName = sc.nextLine();
		
		System.out.println("Next. please tell us your last name");
		String lastName = sc.nextLine();
		
		System.out.println("Finally, please input your desired username");
		String username = sc.nextLine();
		
		Person person = new Person(firstName, lastName, username);
		
		if(personService.addPerson(person)) {
			System.out.println("Your person was added to the database");
		}
		else {
			System.out.println("Something went wrong, account not added");
		}
	}
}