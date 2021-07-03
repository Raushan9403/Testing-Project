package com.object;

public class EMP {
	int id;
	String name;
	Address address;
	
	public EMP(int id,String name,Address address) {
		this.id=id;
		this.name=name;
		this.address=address;
		
	}
	void display() {
		System.out.println(id+" "+name);
		System.out.println(address.city+" "+address.state+" "+address.country);
	}
	
	

	public static void main(String[] args) {
		Address address1=new Address("HZP", "BIHAR", "INDIA");
		Address address2=new Address("MZP"," BIHAR", "INDIA");
		
		EMP emp=new EMP(101, "Shyam", address1);
		EMP emp2=new EMP(102, "Sunny", address2);
		
		emp.display();
		emp2.display();
		

	}

}
