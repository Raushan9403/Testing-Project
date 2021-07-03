package com.object;

class Person{
	int id;
	String name;
	public Person(int id,String name) {
		this.id=id;
		this.name=name;
	
	}
}
class Emp1 extends Person {
	float salary;
	Emp1(int id, String name, float Salary){
	super(id,name);
	this.salary=salary;
	}
	void display() {
		System.out.println(id+" "+name+" "+salary);
	}
}
public class TestSuper {

	public static void main(String[] args) {
		Emp1 e= new Emp1(101,"shiva", 23.09f);
		e.display();

	}

}
