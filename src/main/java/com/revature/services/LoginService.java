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
	
	public boolean getDeposit(int accountNum, double deposit) {
		return loginDAO.setDeposit(accountNum, deposit);
	}
	
	public boolean getWithdrawl(int accountNum, double withdrawl) {
		return loginDAO.setWithdrawl(accountNum, withdrawl);
	}
	
	public boolean getTransfer(int accountNum, int targetAccount, double transfer) {
		return loginDAO.setTransfer(accountNum, targetAccount, transfer);
	}
	
//	public Account getAccount(String username) {
//		return accountDAO.findByUsername(username);
//	}
	
	public List<Account> getAllAccounts (){
		return loginDAO.findAllAccounts();
	}


	
	
	
	
}
