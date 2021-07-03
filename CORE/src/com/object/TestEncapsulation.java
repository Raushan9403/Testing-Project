package com.object;

public class TestEncapsulation {

	public static void main(String[] args) {
		Account1 ac=new Account1();
		ac.setAc_no(12345678);
		ac.setName("Riya");
		ac.setEmail("riya12@gmail.com");
		ac.setAmount(4500);
		System.out.println("Account number::"+ac.getAc_no());
		System.out.println("Account Name::"+ac.getName());
		System.out.println("Account email::"+ac.getEmail());
		System.out.println("Account amount::"+ac.getAmount());

	}

}
