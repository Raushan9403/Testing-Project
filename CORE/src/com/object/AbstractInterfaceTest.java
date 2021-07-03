package com.object;
interface A{
	void a();
	void b();
	void c();
	void d();
}
abstract class B implements A{
	public void c(){System.out.println("i am c:");}
}
 class M extends B{
	public void a() {System.out.println("i am a:");}
	public void b() {System.out.println("i am b:");}
	public void d() {System.out.println("i am d:");}
}

public class AbstractInterfaceTest {

	public static void main(String[] args) {
		A a1=new M();
			a1.a();
			a1.b();
			a1.c();
			a1.d();
		
		
		

	}

}
