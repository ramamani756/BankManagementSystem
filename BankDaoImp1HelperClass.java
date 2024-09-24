package org.jsp.bank.DAO;

public class BankDaoImp1HelperClass {
	public static BankDAO customerHelperDusk()
	{
		BankDAO bankDao=(BankDAO) new BankDaoImp1();
		return bankDao;
	}

}
