package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.Person;
import com.revature.utils.ConnectionUtil;

public class LoginDAOImpl implements LoginDAO {

	@Override
	public String loginCheck(int accountNumber, String username, String password) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT access_type FROM account_info WHERE account_number = ? AND username = ? AND pass_word = ?;";
			
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
	
	// customer methods
	
	@Override
	public boolean setDeposit(int accountNum, double deposit) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT balance FROM account_info WHERE account_number = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, accountNum);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				double balance = result.getDouble(1);
				
				if(deposit < 0) {
					System.out.println("You can't deposit less than 0 dollars, returning");
					System.out.println("Current balance is " +balance);
					return false;
				}
				
				String sql1 = "UPDATE account_info SET balance = ? WHERE account_number = ?;";
				
				PreparedStatement updateBalance = conn.prepareStatement(sql1);
				
				balance = balance + deposit;
				
				updateBalance.setDouble(1, balance);
				updateBalance.setInt(2, accountNum);
				
				updateBalance.executeUpdate();
				
				System.out.println(deposit+ " dollars successfully deposited, your new balance is: $" +balance);
				return true;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean setWithdrawl(int accountNum, double withdrawl) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT balance FROM account_info WHERE account_number = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, accountNum);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				double balance = result.getDouble(1);
				
				if(withdrawl > balance) {
					System.out.println("You can't withdraw more money than you have, returning");
					System.out.println("Current balance is " +balance);
					return false;
				}
				
				String sql1 = "UPDATE account_info SET balance = ? WHERE account_number = ?;";
				
				PreparedStatement updateBalance = conn.prepareStatement(sql1);
				
				balance = balance - withdrawl;
				
				updateBalance.setDouble(1, balance);
				updateBalance.setInt(2, accountNum);
				
				updateBalance.executeUpdate();
				
				System.out.println(withdrawl+ " dollars successfully withdrawn, your new balance is: $" +balance);
				return true;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean setTransfer(int accountNum, int targetAccount, double transfer) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			//select balance from first account
			//check if the transfer amount is greater than balance
			//subtract the transfer amount from sourcebalance
			
			//then pull the targetbalance and add the transfer amount
			
			String sql = "SELECT balance FROM account_info WHERE account_number = ?;";
			
			PreparedStatement getSourceBalance = conn.prepareStatement(sql);
			
			getSourceBalance.setInt(1, accountNum);
			
			ResultSet result = getSourceBalance.executeQuery();
			
			if(result.next()) {
				double sourceBalance = result.getDouble(1);
				
				if(transfer > sourceBalance) {
					System.out.println("You can't transfer more money than you have, returning");
					System.out.println("Current balance is " +sourceBalance);
					return false;
				}
				
				
				String sql1 = "UPDATE account_info SET balance = ? WHERE account_number = ?;";
				
				PreparedStatement updateSourceBalance = conn.prepareStatement(sql1);
				
				sourceBalance = sourceBalance - transfer;
				
				updateSourceBalance.setDouble(1, sourceBalance);
				updateSourceBalance.setInt(2, accountNum);
				
				updateSourceBalance.executeUpdate();
				
				
				// set the target account balance equal to a variable
				
				String sql2 = "SELECT balance FROM account_info WHERE account_number = ?;";
				
				PreparedStatement findTargetBalance = conn.prepareStatement(sql2);
				
				findTargetBalance.setInt(1, targetAccount);
				
				ResultSet result1 = findTargetBalance.executeQuery();
				
				if(result1.next()) {
					double targetBalance = result1.getDouble(1);
				
					String sql3 = "UPDATE account_info SET balance = ? WHERE account_number = ?;";
					
					PreparedStatement updateTargetBalance = conn.prepareStatement(sql3);
					
					targetBalance = targetBalance + transfer;
					
					updateTargetBalance.setDouble(1, targetBalance);
					updateTargetBalance.setInt(2, targetAccount);
					
					updateTargetBalance.executeUpdate();
					
					System.out.println(transfer+ " dollars successfully transferred, the senders new balance is: $" +sourceBalance+ "\n"
							+ "The recipients new balance is: $" +targetBalance);
					return true;
					
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	// employee methods
	
	@Override
	public Account getOneAccount(int targetAccount) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM account_info WHERE account_number = ? AND access_type = 'C';";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, targetAccount);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next() == false) {
				System.out.println("Couldn't find any customer accounts that match that number, returning");
				return null;
			}
			
			Account account = new Account();
			account.setAccountNumber(result.getInt("account_number"));
			account.setUsername(result.getString("username"));
			account.setPassword(result.getString("pass_word"));
			account.setAccessType(result.getString("access_type"));
			account.setAccountType(result.getString("account_type"));
			account.setBalance(result.getDouble("balance"));
			System.out.println(account.getAccountNumber());
			return account;

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Person getOnePerson(String targetUsername) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM user_info WHERE username = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, targetUsername);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next() == false) {
				System.out.println("Couldn't find any user that matches that username, returning");
				return null;
			}
			
			Person person = new Person();
			person.setUsername(result.getString("username"));
			person.setFirstName(result.getString("first_name"));
			person.setLastName(result.getString("last_name"));
			return person;

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	// admin methods
	
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
				account.setAccountType(result.getString("account_type"));
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
	public void deleteAccount(int targetAccount) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM account_info WHERE account_number = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, targetAccount);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next() == false) {
				System.out.println("Couldn't find any account that matches that account number, returning");
				return;
			}
			
			String sql1 = "DELETE FROM account_info WHERE account_number = ?;";
			
			PreparedStatement deleteAccount = conn.prepareStatement(sql1);
			
			deleteAccount.setInt(1, targetAccount);
			
			deleteAccount.executeUpdate();
			
			System.out.println("Account number " +targetAccount+ " successfully deleted");
			
			return;
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return;
		
	}



}
