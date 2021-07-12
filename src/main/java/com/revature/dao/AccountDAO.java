package com.revature.dao;

import java.util.*;
import com.revature.models.*;

public interface AccountDAO {

	public List<Account> findAllAccounts();
	public Account findByUsername(String username);
	public boolean updateAccount(Account account);
	public boolean addAccount(Account account);
}
