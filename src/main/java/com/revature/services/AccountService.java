package com.revature.services;

import java.util.List;

import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.models.Account;

public class AccountService {

	//start of loosely coupling
	//calling the interface for abstraction and to let us change the implementation easily
	private static AccountDAO accountDAO = new AccountDAOImpl();
	
	public List<Account> getAllAccounts (){
		return accountDAO.findAllAccounts();
	}
	
	public Account getAccount(String username) {
		return accountDAO.findByUsername(username);
	}
	
	public boolean addAccount(Account account) {
		return accountDAO.addAccount(account);
	}
	
}
