package net.sf.jiffie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class TimeParser {
	
	public static final String[] BOUNDS_ARRAY = {"Tripod","Flap Barrier","Basement Entrance","Basement Tripod"}; 
	
	public static ArrayList<DailyTime> weekTimeParser(IHTMLDocument2 document)
	{
		ArrayList<DailyTime> weekDayTimeList = new ArrayList<DailyTime>();
		
		
		
		return weekDayTimeList;
	}
	
	
	
	public static long DayTimeParser(IHTMLDocument2 document)
	{
		int dateColumn = 2;
		int machineColumn = 3;
		int directionColumn = 4;
		int timeColumn = 5;
		long totalTimeInSeconds = 0;
		ArrayList<ParsedTimeData> directionTime = new ArrayList<ParsedTimeData>();
		
		
		ElementList tableList=null;
		IHTMLTable swipeTable = null;
		SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy hh:mm:ss a");
		try {
			tableList = document.getElementListByTag("TABLE");
		} catch (JiffieException e) {
			System.out.println("In TimeParser.DayTimeParser() - No table tag found");
			e.printStackTrace();
		}
		
		for (int i=0; i<tableList.size();i++)
		{
			if(((IHTMLTable)tableList.get(i)).getInnerHtml().contains("10557"))
			{
//				System.out.println(((IHTMLTable)tableList.get(i)).getInnerHtml());
				swipeTable = (IHTMLTable)tableList.get(i);
			}
		}
		
		try {
			for (int i=0; i<swipeTable.getRows().size();i++)
				{
					if(swipeTable.getRow(i).getInnerHtml().contains("10557"))
					{
						Date time = format.parse (swipeTable.getRow(i).getCell(dateColumn - 1).getInnerText() + " " + swipeTable.getRow(i).getCell(timeColumn - 1).getInnerText());
						if(swipeTable.getRow(i).getCell(machineColumn - 1).getInnerText().contains(BOUNDS_ARRAY[0]) || 
						   swipeTable.getRow(i).getCell(machineColumn - 1).getInnerText().contains(BOUNDS_ARRAY[1])	||
						   swipeTable.getRow(i).getCell(machineColumn - 1).getInnerText().contains(BOUNDS_ARRAY[2]) ||
						   swipeTable.getRow(i).getCell(machineColumn - 1).getInnerText().contains(BOUNDS_ARRAY[3]))
						{
							directionTime.add(new ParsedTimeData(swipeTable.getRow(i).getCell(directionColumn - 1).getInnerText(), time));
							System.out.println(swipeTable.getRow(i).getCell(directionColumn - 1).getInnerText() + " - " + swipeTable.getRow(i).getCell(timeColumn - 1).getInnerText() + "date = " + time.getTime()/1000);
						}
					}
				}
			System.out.println(new Date().getTime()/1000);
		} catch (JiffieException e) {
			System.out.println("Exception while getting rows from swipetable in TimeParser.DayTimeParser");
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println("Exception while parsing the date and time in TimeParser.DayTimeParser");
			e.printStackTrace();
		}
		
		Date currentDate = null;
		try {
			currentDate = format.parse (swipeTable.getRow(2).getCell(dateColumn - 1).getInnerText() + " 00:00:00 AM");
		} catch (ParseException e) {
			System.out.println("Error Parsing Current Date in TimeParser.DayTimeParser()");
			e.printStackTrace();
		}
//		System.out.println(currentDate);
		totalTimeInSeconds = inTimeCalculator(directionTime,currentDate);
					
		return totalTimeInSeconds;
	}
	
	public static long inTimeCalculator(ArrayList<ParsedTimeData> parsedData, Date time)
	{
		long totalTimeInSeconds = 0;
		Boolean personInLimits = false;
		Boolean personEntered = false;
		Boolean personExited = false;
		Date lastExit = time;
		Date lastIn = time;		
		ParsedTimeData currentRow = null;
		Iterator itr=parsedData.iterator();
		
		while(itr.hasNext())
		{
			currentRow = (ParsedTimeData) itr.next();
			if(currentRow.direction.contains("Entry"))
			{
				if(!personInLimits)
				{
					personInLimits = true;
				}
				lastIn = currentRow.time;
			}
			else
			{
				lastExit = currentRow.time;
				if(personInLimits)
				{
					System.out.println(lastExit.getSeconds());
					totalTimeInSeconds = totalTimeInSeconds + ((lastExit.getTime() - lastIn.getTime())/1000);
					personInLimits = false;
				}
			}
		}
		if(personInLimits)
		{
			totalTimeInSeconds = totalTimeInSeconds + ((new Date().getTime() - lastIn.getTime())/1000);
		}
		
		System.out.println("total = " + totalTimeInSeconds);
		long seconds = totalTimeInSeconds % 60;
	    long totalMinutes = totalTimeInSeconds / 60;
	    long minutes = totalMinutes % 60;
	    long hours = totalMinutes / 60;
		
	    System.out.println(hours + " hours " + minutes + " minutes " + seconds + " seconds ");
	    
		return totalTimeInSeconds;
	}
	
}



class ParsedTimeData
{
	String direction;
	Date time;
	
	public ParsedTimeData(String direction, Date time)
	{
		this.direction = direction;
		this.time = time;
	}

	@Override
	public String toString() {
		return "ParsedTimeData [direction=" + direction + ", time=" + time
				+ "]";
	}

	
}