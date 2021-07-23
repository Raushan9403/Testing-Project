package com.object;
class Bank5{
	static String branchName="HYD";
	static String ifc="hdfc01";
	String accHoldername;
	double balance,accno;

	public Bank5(String a,double d,double b) {
		accHoldername=a;
		balance=d;
		accno=b;
	}
	void display() {
		System.out.println(branchName+" "+ifc+" "+accHoldername+" "+balance+" "+accno);
		
	}
}
public class TestBankAccount {
	
	public static void main(String[] args) {
		Bank5 b1=new Bank5("HK", 10000, 6777767);
		Bank5 b2=new Bank5("BL", 20000, 7677);
		b1.display();
       b2.display();
	}

}
