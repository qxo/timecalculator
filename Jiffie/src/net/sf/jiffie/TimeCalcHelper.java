package net.sf.jiffie;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class TimeCalcHelper {
	public static long requiredMonthSeconds; 
	public static long workingDays;
	public static Calendar start = Calendar.getInstance();
	public static Calendar end = Calendar.getInstance();
	
	static {
		Date monthStart = new Date();
		monthStart.setDate(1);
		monthStart.setHours(0);
		monthStart.setMinutes(0);
		monthStart.setSeconds(0);
		start.setTime(monthStart);
		end.setTime(new Date());
		end.set(Calendar.DAY_OF_MONTH, end.getActualMaximum(Calendar.DAY_OF_MONTH));
//		System.out.println("End = " + end);
//		System.out.println("Month Start  = "+ monthStart );
		
		for(int i = start.get(Calendar.DATE); i <= end.get(Calendar.DATE); i++)
		{
			if(!(start.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || start.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY))
			{
				workingDays = workingDays + 1;
			}
			System.out.println(start.get(Calendar.DATE));
			System.out.println("Working days = " + workingDays);
			start.add(Calendar.DATE, 1);
		}
		requiredMonthSeconds = workingDays * 8 * 60 * 60;
//		System.out.println("Required Month Seconds = " + requiredMonthSeconds);
	}
	
	
	public static void init(){};
	
	public static ArrayList<WeekTime> weeklyTime(ArrayList<DailyTime> dayTimeList)
	{
		Iterator iterator = dayTimeList.iterator();
		ArrayList<WeekTime> weeklyTime = new ArrayList<WeekTime>();
		WeekTime currentWeek = new WeekTime("Week1", 0);
		int count = 1;
		int weekCounter = 1;
		while (iterator.hasNext()) {
			DailyTime day = (DailyTime) iterator.next();
			
			currentWeek.setTimeInSeconds(currentWeek.getTimeInSeconds() + day.getInTimeInSeconds());
			if (day.getDate().getDate() == 7 || day.getDate().getDate() == 14 || day.getDate().getDate() == 21 || day.getDate().getDate() == 28 || day.getDate().getDate() == end.getActualMaximum(Calendar.DAY_OF_MONTH))
			{
				weeklyTime.add(currentWeek);
				currentWeek = new WeekTime("Week" + weekCounter, 0);
				weekCounter++;
			}
			
			count++;
			
		}
		return weeklyTime;
	}
	
	public static long TotalMonthTime(ArrayList<DailyTime> dayTimeList)
	{
		long totalMonthTime=0;
		for (int i=0; i <= dayTimeList.size() - 1; i++ )
		{
			totalMonthTime = totalMonthTime + dayTimeList.get(i).getInTimeInSeconds();
		}
		
		return totalMonthTime; 
	}

	
	
}
