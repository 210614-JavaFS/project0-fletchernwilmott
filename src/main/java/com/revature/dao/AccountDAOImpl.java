package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.utils.ConnectionUtil;


public class AccountDAOImpl implements AccountDAO {

	@Override
	public List<Account> findAllAccounts() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM account_info";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<Account> accountList = new ArrayList<>();
			
			//Result sets have a cursor similar to Scanners
			//moves the cursor forward one row, returns true if there is something
			while(result.next()) {
				Account account = new Account();
				account.setUsername(result.getString("username"));
				account.setPassword(result.getString("pass_word"));
				account.setAccessType(result.getString("access_type"));
				account.setBalance(result.getDouble("balance"));
				accountList.add(account);
			}
			
			return accountList;
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Account findByUsername(String username) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM account_info WHERE username = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			//this is where sql injection is checked for
			statement.setString(1, username);
			
			ResultSet result = statement.executeQuery();
			
			Account account = new Account();
			
			//Result sets have a cursor similar to Scanners
			//moves the cursor forward one row, returns true if there is something
			while(result.next()) {
				account.setUsername(result.getString("username"));
				account.setPassword(result.getString("pass_word"));
				account.setAccessType(result.getString("access_type"));
				account.setBalance(result.getDouble("balance"));
			}
			
			return account;
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateAccount(Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAccount(Account account) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "INSERT INTO accounts (username, pass_word, access_type, balance)"
					+ "VALUES(?, ?, ?, ?);";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index = 0;
			
			statement.setString(++index, account.getUsername());
			statement.setString(++index, account.getPassword());
			statement.setString(++index, account.getAccessType());
			statement.setDouble(++index, account.getBalance());
			
			statement.execute();
			
			return true;
				
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	
	
	
	
}
