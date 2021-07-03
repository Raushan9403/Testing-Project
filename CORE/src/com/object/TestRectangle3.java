package com.object;
 class Rectangle4{
	 int length;
	 int widgth;
	 
	 void insert(int l,int w) {
		 length=l;
		 widgth=w;
		 
	 }
	 void calculateArea() {
		 System.out.println(length*widgth);
	 }
 }
public class TestRectangle3 {

	public static void main(String[] args) {
		Rectangle r1=new Rectangle(), r2=new Rectangle();
		r1.insert(12, 5);
		r2.insert(5, 6);
		
		r1.calculateArea();
		r2.calculateArea();

	}

}
