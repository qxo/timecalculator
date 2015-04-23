package net.sf.jiffie;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.html.HTMLButtonElement;
import org.w3c.dom.html.HTMLTableElement;



public class MyTest {

	public static void main(String[] args) throws Exception {
		System.out.println(System.getProperty("sun.arch.data.model"));
		TimeCalcHelper.init();
		//Time calculation for Today		
		
		InternetExplorer myExplorer = new InternetExplorer();
		myExplorer.setVisible(true);
		myExplorer.navigate("http://cybmis-app-3-ld/Report%20Builder/RPTN/Reportpage.aspx");
		
		
		Thread.sleep(1500);
		IHTMLDocument2 doc = myExplorer.getDocument(true);
		IHTMLAnchorElement aTag;

		aTag = (IHTMLAnchorElement)doc.getElementById("TempleteTreeViewt6");
		aTag.click(true);
		
		
		Thread.sleep(1000);
		IHTMLInputElement sub1 = (IHTMLInputElement)doc.getElementById("ViewReportImageButton");
		sub1.click(true);
		doc = myExplorer.getDocument(true);
		
		long totalSecondsToday = TimeParser.DayTimeParser(doc);
		myExplorer.quit();
		myExplorer=null;
		
		//Time calculation for yesterday
		
		myExplorer = new InternetExplorer();
		myExplorer.setVisible(true);
		myExplorer.navigate("http://cybmis-app-3-ld/Report%20Builder/RPTN/Reportpage.aspx");
		
		Thread.sleep(1500);
		doc = myExplorer.getDocument(true);
		aTag = (IHTMLAnchorElement)doc.getElementById("TempleteTreeViewt6");
		aTag.click(true);
		
		
		Thread.sleep(1500);
		IHTMLSelectElement selectInput = (IHTMLSelectElement)doc.getElementById("DayDropDownList8665");
		selectInput.setSelectedIndex(2);
		
		
		Thread.sleep(1000);
		sub1 = (IHTMLInputElement)doc.getElementById("ViewReportImageButton");
		sub1.click(true);
		doc = myExplorer.getDocument(true);
		
		long totalSecondsYesterday = TimeParser.DayTimeParser(doc);
		myExplorer.quit();
		myExplorer=null;
				
		Date todaysDate = new Date();
		
		
		
		//Weekly Calculation
		Thread.sleep(1000);
		myExplorer = new InternetExplorer();
		myExplorer.setVisible(true);
		myExplorer.navigate("http://cybmis-app-3-ld/Report%20Builder/RPTN/Reportpage.aspx");
		
		Thread.sleep(1500);
		doc = myExplorer.getDocument(true);
		aTag = (IHTMLAnchorElement)doc.getElementById("TempleteTreeViewt4");
		aTag.click(true);
		
		Thread.sleep(1500);
		IHTMLInputElement dateFrom = (IHTMLInputElement)doc.getElementById("DMNDateDateRangeControl4392_FromDateCalender");
		
		dateFrom.setValue("1-Apr-2015");
		
		dateFrom = (IHTMLInputElement)doc.getElementById("DMNDateDateRangeControl4392_FromDateCalender_DTB");
		dateFrom.setValue("1-Apr-2015");
		
		sub1 = (IHTMLInputElement)doc.getElementById("ViewReportImageButton");
		
		sub1.click(true);
		
		doc = myExplorer.getDocument(true);
		
		ArrayList<DailyTime> weekDayTimes = TimeParser.weekTimeParser(doc);
		myExplorer.quit();
		myExplorer=null;
		
		
		if (todaysDate.getDate() != 1)
		{
			weekDayTimes.add(new DailyTime(new Date(todaysDate.getYear(),todaysDate.getMonth(), todaysDate.getDate() - 1), totalSecondsYesterday));
		}
		weekDayTimes.add(new DailyTime(new Date(), totalSecondsToday));
		
		long totalTimeInSeconds = TimeCalcHelper.TotalMonthTime(weekDayTimes);
		
		
		long seconds = totalTimeInSeconds % 60;
	    long totalMinutes = totalTimeInSeconds / 60;
	    long minutes = totalMinutes % 60;
	    long hours = totalMinutes / 60;
		
	    System.out.println(hours + " hours " + minutes + " minutes " + seconds + " seconds ");
	    
	    System.out.println("Weeks  = " + TimeCalcHelper.weeklyTime(weekDayTimes).size());
		
	}

}
