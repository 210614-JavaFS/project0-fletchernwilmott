package com.revature.services;

import java.util.List;

import com.revature.dao.LoginDAO;
import com.revature.dao.LoginDAOImpl;
import com.revature.models.Account;
import com.revature.models.Person;

public class LoginService {

	private static LoginDAO loginDAO = new LoginDAOImpl();
	
	public String loginCheck(int accountNumber, String username, String password) {
		return loginDAO.loginCheck(accountNumber, username, password);
	}
	
	
	// customer methods
	
	public boolean getDeposit(int accountNum, double deposit) {
		return loginDAO.setDeposit(accountNum, deposit);
	}
	
	public boolean getWithdrawl(int accountNum, double withdrawl) {
		return loginDAO.setWithdrawl(accountNum, withdrawl);
	}
	
	public boolean getTransfer(int accountNum, int targetAccount, double transfer) {
		return loginDAO.setTransfer(accountNum, targetAccount, transfer);
	}
	
	
	// employee methods
	
	public Account getOneAccount(int targetAccount) {
		return loginDAO.getOneAccount(targetAccount);
	}
	
	public Person getOnePerson(String targetUsername) {
		return loginDAO.getOnePerson(targetUsername);
	}
	
//	public Account getAccount(String username) {
//		return accountDAO.findByUsername(username);
//	}
	
	public List<Account> getAllAccounts (){
		return loginDAO.findAllAccounts();
	}

	public List<Account> getAllApplications() {
		return loginDAO.findAllApplications();
	}

	public void deleteAccount(int targetAccount) {
		loginDAO.deleteAccount(targetAccount);
	}

	public void approveDenyAccounts(int targetAccount, String applicationResponse) {
		loginDAO.accessApplications(targetAccount, applicationResponse);
	}


	public Account copyOneAccount(int targetAccount) {
		return loginDAO.getAccountCopy(targetAccount);
	}

	public void pasteOneAccount(Account account) {
		loginDAO.pasteAccount(account);
		
	}

	public void deleteApplication(int targetAccount, String applicationResponse) {
		loginDAO.deleteApplication(targetAccount, applicationResponse);
		
	}


}
