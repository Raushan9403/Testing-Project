package com.object;

import java.util.Scanner;

public class TestRectangle2 {

	public static void main(String[] args) {
		Scanner s= new Scanner(System.in);
		System.out.println("Enter the length");
		double l=s.nextDouble();
		System.out.println("Enter the width");
		double b=s.nextDouble();
		 
		double area=l*b;
		System.out.println("Area of Reactangle is:"+area);
				
	}

}
