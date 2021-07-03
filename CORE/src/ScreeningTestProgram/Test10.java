package ScreeningTestProgram;
class Test{
	static int x;
	void increment() {
		x++;
	}
}

public class Test10 {

	public static void main(String[] args) {
		Test obj1=new Test();
		Test obj2=new Test();
		obj1.x=0;
		obj1.increment();
		obj2.increment();
		System.out.println(obj1+" "+obj2);
		

	}

}
