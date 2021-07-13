package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Person;
import com.revature.utils.ConnectionUtil;

public class PersonDAOImpl implements PersonDAO {

	@Override
	public boolean addPerson(Person person) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "INSERT INTO user_info (username, first_name, last_name)"
					+ "VALUES(?, ?, ?);";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index = 0;
			
			statement.setString(++index, person.getUsername());
			statement.setString(++index, person.getFirstName());
			statement.setString(++index, person.getLastName());
			
			statement.execute();
			
			return true;
				
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	
	
}
