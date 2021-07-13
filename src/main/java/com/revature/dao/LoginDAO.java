package com.revature.dao;

import java.util.*;
import com.revature.models.*;

public interface LoginDAO {
		
	public String loginCheck(int accountNumber, String username, String password);
	
	// customer methods
	public boolean setDeposit(int accountNum, double deposit);
	public boolean setWithdrawl(int accountNum, double withdrawl);
	public boolean setTransfer(int accountNum, int targetAccount, double transfer);
	
	// employee methods
	public Account getOneAccount(int targetAccount);
	public Person getOnePerson(String targetUsername);
	
	// admin methods
	public List<Account> findAllAccounts();

	

	
}
