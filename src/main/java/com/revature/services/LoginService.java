package com.revature.services;

import java.util.List;

import com.revature.dao.LoginDAO;
import com.revature.dao.LoginDAOImpl;
import com.revature.models.Account;

public class LoginService {

	private static LoginDAO loginDAO = new LoginDAOImpl();
	
	public String loginCheck(int accountNumber, String username, String password) {
		return loginDAO.loginCheck(accountNumber, username, password);
	}
	
	public double getDeposit(Double deposit) {
		return loginDAO.setDeposit(deposit);
	}
	
//	public Account getAccount(String username) {
//		return accountDAO.findByUsername(username);
//	}
	
	public List<Account> getAllAccounts (){
		return loginDAO.findAllAccounts();
	}
	
	
	
}
