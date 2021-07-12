package com.revature.dao;

import java.util.*;
import com.revature.models.*;

public interface LoginDAO {
		
	public String loginCheck(int accountNumber, String username, String password);
	public List<Account> findAllAccounts();
	public double setDeposit(Double deposit);
}
