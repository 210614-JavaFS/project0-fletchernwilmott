package models;

public abstract class AccountCreator {

	String username;
	String password;
	char type;
	double balance;
	
	// constructor for all accounts
	AccountCreator(String username, String password, char type){
		
		// account type can be C for customer, E for employee, or A for admin
		this.username = username;
		this.password = password;
		this.type = type;
		this.balance = 0;
		
	}
	
	
}
