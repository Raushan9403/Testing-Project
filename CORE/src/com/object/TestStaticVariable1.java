package com.object;
class Student3{
	int roll_no;
	String sname;
	static String college="DPS";
	
	public Student3(int r, String s) {
		roll_no=r;
		sname=s;
		
	}
	void display() {
		System.out.println(roll_no+" "+sname+" "+college);
		
	}
}
public class TestStaticVariable1 {

	public static void main(String[] args) {
		Student3 s1=new Student3(111, "Ram");
		Student3 s2=new Student3(222, "shyam");
		//Student3.college="BBDIT";
		s1.display();
		s2.display();
		

	}

}
