package com.object;

 class Student {
	int id;
	String name;
}
public class Student1{
	public static void main(String args[]) {
		Student s1=new Student();
		Student s2=new Student();
		
		s1.id=101;
		s1.name="riya";
		s2.id=102;
		s2.name="Raushan";
	
		System.out.println(s1.id+" "+s1.name);
		System.out.println(s2.id+" "+s2.name);
	
	}
}
