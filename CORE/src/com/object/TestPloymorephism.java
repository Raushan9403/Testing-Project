package com.object;
 class Bank{
	float getRateOfIntrest() {
		return 0;
	}
}
 class SBI extends Bank{
	float getRateOfIntrest() {
		return 8.4f;
	}
}
class ICICI extends Bank{
	float getRateOfIntrest() {
		return 7.3f;
	}
}
 class AXIS extends Bank{
	float getRateOfIntrest() {
		return 9.7f;
	}
}
public class TestPloymorephism {

	public static void main(String[] args) {
		Bank b;
		b=new SBI();
		System.out.println("SBI Rate or Bank Intrest::"+b.getRateOfIntrest());
		b=new ICICI();
		System.out.println("ICICI Rate or Bank Intrest::"+b.getRateOfIntrest());
		b=new AXIS();
		System.out.println("AXIS Rate or Bank Intrest::"+b.getRateOfIntrest());

	}

}
