package com.object;
class Employe{
	int id;
	String name;
	float Salary;
	
	void instertRecord(int i,String n, float s) {
		id=i;
		name=n;
		Salary=s;
	}
	void displayInformation() {
		System.out.println(id+" "+name+" "+Salary);
	}
	}
public class TestEmploye {

	public static void main(String[] args) {
		Employe e1=new Employe();
		Employe e2=new Employe();
		
		e1.instertRecord(101, "Ram",1000);
        e2.instertRecord(102, "Rama",1200);
        
        e1.displayInformation();
        e2.displayInformation();
        
	}

}
