package net.sf.jiffie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestTime {

	public static void main(String[] args) throws ParseException  {
		SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy hh:mm:ss a");

//		String dateString = format.format( new Date()   );
		Date   date       = format.parse ( "07 Feb 2015 01:54:30 AM" );  
		System.out.println(date.toString());
		
		Date todaysDate = new Date();
		System.out.println(todaysDate);
		
		long secondsDiff = (todaysDate.getTime() - date.getTime())/1000;
		
		System.out.println(date.getTime());
		System.out.println(todaysDate.getTime());
		
		
		System.out.println((secondsDiff/60.0)/60.0);
	}

}
