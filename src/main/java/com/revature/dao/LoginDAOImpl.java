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

public class LoginDAOImpl implements LoginDAO {

	@Override
	public String loginCheck(int accountNumber, String username, String password) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT access_type FROM account_info WHERE account_number = ? AND username = ? AND pass_word = ?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, accountNumber);
			statement.setString(2, username);
			statement.setString(3, password);
			
			ResultSet result = statement.executeQuery();
			
			Account account = new Account();
			
			if(result.next()) {
				accountNumber = account.getAccountNumber();
				username = account.getUsername();
				password = account.getPassword();
				String accessType = result.getString(1);
				System.out.println("Login successful");
				return accessType;
			}
			else {
				System.out.println("Couldn't find that username or password, or they didn't match");
				return "N";
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return "N";
	}
	
	
	@Override
	public double setDeposit(Double deposit) {
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
			
			return 2;
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	
	@Override
	public List<Account> findAllAccounts() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM account_info";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			ResultSet result = statement.executeQuery();
			
			List<Account> accountList = new ArrayList<>();
			
			//Result sets have a cursor similar to Scanners
			//moves the cursor forward one row, returns true if there is something
			while(result.next()) {
				Account account = new Account();
				account.setAccountNumber(result.getInt("account_number"));
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

//	@Override
//	public Account findByUsername(String username) {
//		try(Connection conn = ConnectionUtil.getConnection()){
//			String sql = "SELECT * FROM account_info WHERE username = ?;";
//			
//			PreparedStatement statement = conn.prepareStatement(sql);
//			
//			//this is where sql injection is checked for
//			statement.setString(1, username);
//			
//			ResultSet result = statement.executeQuery();
//			
//			Account account = new Account();
//			
//			//Result sets have a cursor similar to Scanners
//			//moves the cursor forward one row, returns true if there is something
//			while(result.next()) {
//				account.setUsername(result.getString("username"));
//				account.setPassword(result.getString("pass_word"));
//				account.setAccessType(result.getString("access_type"));
//				account.setBalance(result.getDouble("balance"));
//			}
//			
//			return account;
//			
//		}
//		catch(SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	
	
	
	
}
