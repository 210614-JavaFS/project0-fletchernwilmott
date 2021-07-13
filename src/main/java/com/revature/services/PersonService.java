package com.revature.services;

import java.util.List;

import com.revature.dao.PersonDAO;
import com.revature.dao.PersonDAOImpl;
import com.revature.models.Person;

public class PersonService {

	private static PersonDAO personDAO = new PersonDAOImpl();
	
	public boolean addPerson(Person person) {
		return personDAO.addPerson(person);
	}
	
}
