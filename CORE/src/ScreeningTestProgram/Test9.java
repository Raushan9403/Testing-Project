package ScreeningTestProgram;
class newthread implements Runnable{
	Thread t;
	public void run() {
		System.out.println(Thread.currentThread());
	}
	newthread(){
    t=new Thread(this,"hyd");
    t.start();
	}
}
public class Test9 {

	public static void main(String[] args) {
		new newthread();

	}

}
