package ScreeningTestProgram;

public class Test4 {
static void throwexception() throws ArithmeticException
{
	System.out.println("O");
	throw new ArithmeticException("Exception");
	
}
	public static void main(String[] args) {
		try {
			throwexception();
		}
		catch (ArithmeticException e) {
        System.out.println("A");
		}
	

	}

}
