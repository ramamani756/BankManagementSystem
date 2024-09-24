package org.jsp.bank.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import org.jsp.bank.model.Bank;

//import com.jsp.user_information.OtpMismatchException;

public class BankDaoImp1 implements BankDAO {
	String url="jdbc:mysql://localhost:3306/teca52?user=root&password=12345";
	Scanner sc=new Scanner(System.in);
	public void userRegistration(Bank bank) {
		// TODO Auto-generated method stub
		String insert="insert into bank(firstname, lastname, mobilenumber, emailid, password, address, amount, accountnumber) values(?,?,?,?,?,?,?,?)";
		//String url="jdbc:mysql://localhost:3306/teca52?user=root&password=12345";
		try {
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(insert);
			preparedStatement.setString(1,bank.getFirstName());
			preparedStatement.setString(2,bank.getLastName());
			preparedStatement.setString(3,bank.getMobileNumber());
			//generating email by usig first name and random digits
			String tempName=bank.getFirstName().toLowerCase();
			Random random=new Random();
			int tempnum=random.nextInt(1000);
			String bankEmailId=tempName+tempnum+"@teca52.com";
			
			preparedStatement.setString(4,bankEmailId);
			preparedStatement.setString(5,bank.getPassword());
			preparedStatement.setString(6,bank.getAddress());
			preparedStatement.setDouble(7, bank.getAmount());
			//generating accountNumber by using random numbers
			long ac=random.nextLong(100000000000l);
			if(ac<10000000000l)
			{
				ac+=10000000000l;
			}
			preparedStatement.setString(8,""+ac);
			int result=preparedStatement.executeUpdate();
			if(result!=0)
			{
				System.out.println("your account successfully created");
				try {
					Thread.sleep(2000);
					System.out.println("your bank email id="+bankEmailId);
					System.out.println("your accountNumber="+ac);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				System.out.println("not created your account");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	public void credit(String accountNumber,String password) {
		// TODO Auto-generated method stub
		try {
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement("select * from bank where accountnumber=? and password=?");
			preparedStatement.setString(1,accountNumber);
			preparedStatement.setString(2, password);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
			{
				System.out.println("enter your amount");
				boolean amountStatus=true;
				while(amountStatus)
				{
					double userAmount=sc.nextDouble();
					if(userAmount>=0)
					{
						amountStatus=false;
						double dataBaseAmount=resultSet.getDouble("amount");
						/*if(dataBaseAmount>=userAmount)
						{*/
							double add=dataBaseAmount+userAmount;
							//System.out.println("Remaining amount="+sub);
							String update="update bank set amount=? where accountnumber=? and password=? ";
							PreparedStatement preparedStatementUpdate=connection.prepareStatement(update);
							preparedStatementUpdate.setDouble(1, add);
							preparedStatementUpdate.setString(2, accountNumber);
							preparedStatementUpdate.setString(3, password);
							int result=preparedStatementUpdate.executeUpdate();
							if(result!=0)
							{
								for(int i=0;i<=5;i++)
								{
									try {
										Thread.sleep(3000);
										System.out.print(".");
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								System.out.println("Amount Credit Successfully.....");
								System.out.println("Total Amount in Account="+add);
							}
							else {
								for(int i=0;i<=5;i++)
								{
									try {
										Thread.sleep(3000);
										System.out.print(".");
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								System.out.println("Not Credit Successfull....");
							}
						}
						/*else {
							System.out.println("Insufficient Amount");
							amountStatus=true;
						}
					}*/
					else {
						System.out.println("Invalid amount");
						System.out.println("Enter Amount Greater Than Zero");
					}
				}
			}
			else {
				System.out.println("Not Okey...");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	
	public void debit(String accountNumber,String password) {
		// TODO Auto-generated method stub
		try {
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement("select * from bank where accountnumber=? and password=?");
			preparedStatement.setString(1,accountNumber);
			preparedStatement.setString(2, password);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
			{
				System.out.println("enter your amount");
				boolean amountStatus=true;
				while(amountStatus)
				{
					double userAmount=sc.nextDouble();
					if(userAmount>=0)
					{
						amountStatus=false;
						double dataBaseAmount=resultSet.getDouble("amount");
						if(dataBaseAmount>=userAmount)
						{
							double sub=dataBaseAmount-userAmount;
							//System.out.println("Remaining amount="+sub);
							String update="update bank set amount=? where accountnumber=? and password=? ";
							PreparedStatement preparedStatementUpdate=connection.prepareStatement(update);
							preparedStatementUpdate.setDouble(1, sub);
							preparedStatementUpdate.setString(2, accountNumber);
							preparedStatementUpdate.setString(3, password);
							int result=preparedStatementUpdate.executeUpdate();
							if(result!=0)
							{
								for(int i=0;i<=5;i++)
								{
									try {
										Thread.sleep(3000);
										System.out.print(".");
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								System.out.println("Amount Debit Successfully.....");
								System.out.println("Total Amount in Account="+sub);
							}
							else {
								for(int i=0;i<=5;i++)
								{
									try {
										Thread.sleep(3000);
										System.out.print(".");
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								System.out.println("Not Debit Successfull....");
							}
						}
						else {
							System.out.println("Insufficient Amount");
							amountStatus=true;
						}
					}
					else {
						System.out.println("Invalid amount");
						System.out.println("Enter Amount Greater Than Zero");
					}
				}
			}
			else {
				System.out.println("Not Okey...AccountNumber And Password Not Found in Data Base");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	

	public void changingThePassword(String accountNumber,String password) {
		// TODO Auto-generated method stub
		System.out.println("enter new password");
		boolean newPasswordStatus=true;
		while(newPasswordStatus)
		{
		String newPassword=sc.next();
		if(newPassword.length()==5)
		{
			newPasswordStatus=false;
		System.out.println("enter the confrom password");
		String confromPassword=sc.next();
		boolean passwordStatus=true;
		while(passwordStatus)
		{
			if(newPassword.equals(confromPassword))
			{
				passwordStatus=false;
				try {
					Connection connection=DriverManager.getConnection(url);
					PreparedStatement preparedStatement=connection.prepareStatement("update bank set password=? where accountnumber=? and password=?");
					preparedStatement.setString(1, confromPassword);
					preparedStatement.setString(2, accountNumber);
					preparedStatement.setString(3, password);
					int result=preparedStatement.executeUpdate();
					if(result!=0)
					{
						Random random=new Random();
						int otp=random.nextInt(1000000);
						if(otp<100000)
						{
							otp+=100000;
						}
						System.out.println("your otp ="+otp);
						System.out.println("enter the generated otp");
						boolean otpStatus=true;
						while(otpStatus)
						{
						
							int confromOtp=sc.nextInt();
								if(otp==confromOtp)
								{
									otpStatus=false;
									System.out.println("updated successfully");
								}
								
								else {
									System.out.println("enter the correct generated otp");
								}
		
					        }
					}
					else {
						System.out.println("not updated successfully");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				System.out.println("Re-enter the confromPassword");
				confromPassword=sc.next();
			}
		}	
	}
		else {
			System.out.println("Re-enter the new Password must contains 5 digits...");
		}
		}
	}

	public void mobileToMobileTransaction(String mobileNumber) {
		// TODO Auto-generated method stub
		try {
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement("select * from bank where mobilenumber=?");
			preparedStatement.setString(1, mobileNumber);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
			{
				System.out.println("enter receiver mobile number");
				boolean revMobileNumberStatus=true;
				while(revMobileNumberStatus)
				{
					String revMobileNumber=sc.next();
					if(revMobileNumber.length()==10)
					{
						revMobileNumberStatus=false;
						//System.out.println("okey...");
						PreparedStatement ps1=connection.prepareStatement("select * from bank where mobilenumber=?");
						ps1.setString(1, revMobileNumber);
						ResultSet rs1=ps1.executeQuery();
						if(rs1.next())
						{
							System.out.println("enter Amount");
							boolean amountStatus=true;
							while(amountStatus)
							{
							double amount=sc.nextDouble();
							if(amount>0)
							{
								amountStatus=false;
								double senderDataBaseAmount=resultSet.getDouble("amount");
								double subAmount=senderDataBaseAmount-amount;
								if(senderDataBaseAmount>=amount)
								{
									//double subAmount=senderDataBaseAmount-amount;
									String senderUpdate="update bank set amount=? where mobilenumber=?";
									PreparedStatement ps2=connection.prepareStatement(senderUpdate);
									ps2.setDouble(1, subAmount);
									ps2.setString(2,mobileNumber );
									int result=ps2.executeUpdate();
									if(result!=0)
									{
										for(int i=0;i<=5;i++)
										{
											try {
												Thread.sleep(3000);
												System.out.print(".");
											} catch (InterruptedException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
										System.out.println("Amount Debit successfully....");
										//System.out.println("Remaining Amount in Account :"+subAmount);
										double receiverDataBaseAmount=rs1.getDouble("amount");
										double addingAmount=receiverDataBaseAmount+amount;
										if(receiverDataBaseAmount>=amount)
										{
											//double addingAmount=receiverDataBaseAmount+amount;
											String receiverUpdate="update bank set amount=? where mobilenumber=?";
											PreparedStatement ps3=connection.prepareStatement(receiverUpdate);
											ps3.setDouble(1, addingAmount);
											ps3.setString(2,revMobileNumber);
											int recResult=ps3.executeUpdate();
											if(recResult!=0)
											{
			
												System.out.println("Amount Credited successfully....Transaction Successfully...");
												//System.out.println("Remaining Amount in Account :"+addingAmount);
												System.out.println("Do You want to check the balance...\nCheck the sender amount...Enter-Yes\nCome Out the Transaction...Enter-No");
												String response=sc.next();
												if(response.equalsIgnoreCase("yes"))
												{
													System.out.println("Sender Amount in Account :"+subAmount);
												}
												else {
													//System.out.println("Receiver Amount in Account :"+addingAmount);
													System.out.println("ThankYou...Visit Again...");
												}
											}
											else {
												System.out.println("Invalid Mobile Number...Not Received Successfully....");
												double senderTransactionNotSuccessReturnAmount=senderDataBaseAmount+amount;
												String senderAmountUpdate="update bank set amount=? where mobilenumber=?";
												PreparedStatement ps4=connection.prepareStatement(senderAmountUpdate);
												ps2.setDouble(1, senderTransactionNotSuccessReturnAmount);
												ps2.setString(2,mobileNumber );
												int senderReturnAmountResult=ps2.executeUpdate();
												if(senderReturnAmountResult!=0)
												{
													System.out.println("return amount to sender successfully....");
													System.out.println("Sender Amount :"+senderTransactionNotSuccessReturnAmount);
												}
												else {
													System.out.println("not successfully...");
												}
											}
											
										}
										else {
											System.out.println("Insufficient Balance");
											System.out.println("your account balance="+receiverDataBaseAmount+"/-");
										}
									}
									else {
										System.out.println("Invalid Mobile Number...Not Sended Successfully....");
									}
								}
								else {
									System.out.println("Insufficient Balance");
									System.out.println("your account balance="+senderDataBaseAmount+"/-");
								}
								
							}
							else {
								System.out.println("enter valid amount");
							}
							}
						}
						else {
							System.out.println("Enter valid receivers mobile number..Your Entered Mobile number not found in data base");
							System.out.println("Refer Your Friend To Teca52Bank To Get CashBack 100 Rupees......");
						}
					}
					else {
						System.out.println("Enter Valid 10 Digits Receivers Mobile Number");
					}
				}
			}
			else {
				System.out.println("Invalid details....Enter the correct mobile number b/w 10 digits");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void checkBalance(String accountNumber,String password) {
		// TODO Auto-generated method stub
		try {
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement("select amount from bank where accountnumber=? and password=?");
			preparedStatement.setString(1, accountNumber);
			preparedStatement.setString(2, password);
			ResultSet resultSet =preparedStatement.executeQuery();
			if(resultSet.next())
			{
				double userAmount=resultSet.getDouble("amount");
				System.out.println("Your Balance :"+userAmount);
			}
			else {
				System.out.println("Invalid details...");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
