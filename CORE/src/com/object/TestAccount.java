package com.object;
class Account{
	int acc_no;
	String name;
	float amount;
	
	void insert(int a,String n, float amt) {
		acc_no=a;
		name=n;
		amount=amt;
	}
	void deposite(float amt) {
		amount=amount+amt;
		System.out.println(amt+" deposite");
	}
	void withdraw(float amt) {
		if(amount<amt) {
			System.out.println("insufficient balance");
		}else {
				amount=amount-amt;
				System.out.println(amt+"withdrawn");
			}
		}
	void checkBlance() {
		System.out.println("Balance is"+amount);
	}
	void display()
	{
		System.out.println(acc_no+" "+name+" "+amount);
	}
	}

public class TestAccount {

	public static void main(String[] args) {
	Account a1=new Account();
	a1.insert(12345, "Rohan", 5000);
	a1.deposite(1000);
	a1.checkBlance();
	a1.withdraw(7000);
	
	
	

	}

}
