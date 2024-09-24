package org.jsp.bank.DAO;

import org.jsp.bank.model.Bank;

public interface BankDAO {
	void userRegistration(Bank bank);
	void credit(String accountNumber,String password);
	void debit(String accountNumber,String password);
	void changingThePassword(String accountNumber,String password);
	void mobileToMobileTransaction(String mobileNumber);
	void checkBalance(String accountNumber,String password);
	

}
