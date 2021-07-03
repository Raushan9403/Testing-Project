package com.object;

public class Student2 {
     int id;
     String name;
     
     Student2(int i,String n){
    	 id=i;
    	 name=n;
    	 
     }
     

     void display() {
    	 System.out.println(id+" "+name);
     }
	public static void main(String[] args) {
		Student2 s3=new Student2(01, "Ram");
		
		
		s3.display();
	

	}

}
