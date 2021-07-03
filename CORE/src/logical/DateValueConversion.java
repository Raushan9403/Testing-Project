package logical;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateValueConversion {

	public static void main(String[] args) throws Exception {
		//Converting String date value to java.util.Date Class obj
		String s1="21-11-1990"; //dd-MM-yyy
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyy");
		java.util.Date ud1=sdf.parse(s1);
		System.out.println("String Date values::"+s1);
		System.out.println("util Date values::"+ud1);
		
		//Converting java.util.Date class obj to java.sql.Date class obj
		long ms=ud1.getTime(); //gives the milli second that eclapsed b/w
		                                              //ud1 date and time and 1970 jan 1st mid night  00:00 hrs (Eproach standard)
		System.out.println("ms::"+ms);
		java.sql.Date sd1=new java.sql.Date (ms);
		System.out.println("util Date::"+ud1);
		System.out.println("sql Date::"+sd1);
		
		//if String date values pattern is yyy-MM-dd pattern then it can be converted directly to java.sql.Date class obj
		//with out converting to java.utl.Date class obj
		String s2="1990-02-22"; //YYY-MM-dd
		java.sql.Date sqd2=java.sql.Date.valueOf(s2);
		System.out.println("String Date values::"+s2);
		System.out.println("sql date values::"+sqd2);
		
		//converting java.sql.date class obj to java.util.date class obj
		//here we can use java.util.Date class ref to refer java.sql.class obj(java.util.Date is super class java.sql.Date)
		java.util.Date ud2=sqd2;
		System.out.println("sql date::"+sqd2);
		System.out.println("util date::"+ud2);
		
		java.util.Date ud3=new java.util.Date(sqd2.getTime());
		System.out.println("sql date::"+sqd2);
		System.out.println("util date::"+ud3);
		
		//converting java.sql.date class obj / java.util.date class obj to java.String.Date class obj
		SimpleDateFormat sdf2=new SimpleDateFormat("dd-MMM-yyyy");
		String s3=sdf2.format(ud3);
		String s4=sdf2.format(sqd2);
		System.out.println("util date ::"+ud3);
		System.out.println("String date ::"+s3);
		System.out.println("String date ::"+s4);
		
		
		
		
		

	}

}
