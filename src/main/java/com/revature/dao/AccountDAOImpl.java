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
	public boolean addAccount(Account account) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "INSERT INTO account_info (account_number, username, pass_word, access_type, balance)"
					+ "VALUES(?, ?, ?, ?, ?);";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index = 0;
			
			statement.setInt(++index, account.getAccountNumber());
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
