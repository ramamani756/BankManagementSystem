package org.jsp.bank;

import java.util.Scanner;

import org.jsp.bank.DAO.BankDAO;
import org.jsp.bank.DAO.BankDaoImp1HelperClass;
import org.jsp.bank.model.Bank;

//import com.bank.registration.MobileNumberException;
//import com.bank.registration.MobileNumberTooLongException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       Scanner sc=new Scanner(System.in);
       BankDAO bankDao=BankDaoImp1HelperClass.customerHelperDusk();
       //bankDao.userRegistration();
       System.out.println("enter \n 1 for registration.\n 2 for credit.\n 3 for debit.\n 4 for changing the password.\n 5 for mobiletoMobileTransaction.\n 6 for checkBalance.");
       int response=sc.nextInt();
       switch (response) {
	case 1:
		System.out.println("enter your first name");
		String fName=sc.next();
		System.out.println("enter your last name");
		String lName=sc.next();
		System.out.println("enter your mobile number");
		String mNumber=sc.next();
		/*System.out.println("enter your email id");
		String email=sc.next();*/
		System.out.println("enter your password");
		String password=sc.next();
		System.out.println("enter your address");
		String address=sc.next();
		System.out.println("enter your amount");
		double amount=sc.nextDouble();
		boolean amountStatus=true;
		while(amountStatus)
		{
		 //double amount=sc.nextDouble();
		if(amount>=0)
		{
			amountStatus=false;
			amount=amount;
		}
		else {
			System.out.println("Re-Enter the amount");
			amount=sc.nextDouble();
		}
		}
		Bank bank=new Bank(0, fName, lName, mNumber, null, password,address,amount,null);
		bankDao.userRegistration(bank);
		break;
		
	case 2:
		boolean creditStatus=true;
		while(creditStatus)
		{
		System.out.println("enter your account number");
		boolean accountStatus=true;
		while(accountStatus)
		{
			String accountNumber=sc.next();
			if(accountNumber.length()==11)
			{
				accountStatus=false;
				System.out.println("Enter Your Password");
				boolean passwordStatus=true;
				while(passwordStatus)
				{
					String userPassword=sc.next();
					if(userPassword.length()==5)
					{
						passwordStatus=false;
						bankDao.credit(accountNumber, userPassword);
					}
					else {
						System.out.println("Invalid password");
						System.out.println("enter 5 digit password");
						//passwordStatus=true;
					}
					
				}
			}
			else {
				System.out.println("Invalid Account number");
				System.out.println("enter 11 digits account number");
				
			}
		}
		System.out.println("....do you want to continue to debit the amount....!! \n yes \n no");
		String res=sc.next();
		if(res.equalsIgnoreCase("yes"))
		{
			creditStatus=true;
		}
		else {
			creditStatus=false;
			System.out.println("Thank you for visiting......");
		}
		}
		break;
		
		
	case 3:
		boolean debitStatus=true;
		while(debitStatus)
		{
		System.out.println("enter your account number");
		boolean accountSts=true;
		while(accountSts)
		{
			String accountNumber=sc.next();
			if(accountNumber.length()==11)
			{
				accountSts=false;
				System.out.println("Enter Your Password");
				boolean passwordStatus=true;
				while(passwordStatus)
				{
					String userPassword=sc.next();
					if(userPassword.length()==5)
					{
						passwordStatus=false;
						bankDao.debit(accountNumber, userPassword);
					}
					else {
						System.out.println("Invalid password");
						System.out.println("enter 5 digit password");
						//passwordStatus=true;
					}
					
				}
			}
			else {
				System.out.println("Invalid Account number");
				System.out.println("enter 11 digits account number");
				
			}
		}
		System.out.println("....do you want to continue to debit the amount....!! \n yes \n no");
		String res=sc.next();
		if(res.equalsIgnoreCase("yes"))
		{
			debitStatus=true;
		}
		else {
			debitStatus=false;
			System.out.println("Thank you for visiting......");
		}
		}
		break;
		
		
	case 4:
		System.out.println("enter your account number");
		boolean accountStatus=true;
		while(accountStatus)
		{
			String accountNumber=sc.next();
			if(accountNumber.length()==11)
			{
				accountStatus=false;
				System.out.println("Enter Your Password");
				boolean passwordStatus=true;
				while(passwordStatus)
				{
					String userPassword=sc.next();
					if(userPassword.length()==5)
					{
						passwordStatus=false;
						bankDao.changingThePassword(accountNumber, userPassword);
					}
					else {
						//System.out.println("Invalid password");
						System.out.println("Re-enter 5 digit password");
						//passwordStatus=true;
					}
					
				}
			}
			else {
				//System.out.println("Invalid Account number");
				System.out.println("Re-enter 11 digits account number");
				
			}
		}
		break;
		
		
	case 5:
		System.out.println("enter mobile number");
		boolean mobileNumberStatus=true;
		while(mobileNumberStatus)
		{
			String mobileNumber=sc.next();
			if(mobileNumber.length()==10)
			{
				mobileNumberStatus=false;
				bankDao.mobileToMobileTransaction(mobileNumber);
			}
			else {
				System.out.println("enter the correct 10 digit mobile number");
			}
		}
		break;
		
	case 6:
		System.out.println("enter your account number");
		boolean accountSts=true;
		while(accountSts)
		{
			String accountNumber=sc.next();
			if(accountNumber.length()==11)
			{
				accountSts=false;
				System.out.println("Enter Your Password");
				boolean passwordStatus=true;
				while(passwordStatus)
				{
					String userPassword=sc.next();
					if(userPassword.length()==5)
					{
						passwordStatus=false;
						bankDao.checkBalance(accountNumber, userPassword);
					}
					else {
						System.out.println("Invalid password.....enter 5 digit password");
					}
					
				}
			}
			else {
				System.out.println("Invalid Account number...enter 11 digits account number");
				
				
			}
		}
		break;

	default:
		break;
	}
    }
}
