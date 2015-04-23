package net.sf.jiffie;

import java.util.Date;

public class DailyTime {

	private Date date;
	private long inTimeInSeconds;
	
	public DailyTime(Date date, long inTimeInSeconds) {
		super();
		this.date = date;
		this.inTimeInSeconds = inTimeInSeconds;
		System.out.println(this.toString());
	}
	@Override
	public String toString() {
		return "DailyTime [date=" + date + ", inTimeInSeconds="
				+ inTimeInSeconds + "]";
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public long getInTimeInSeconds() {
		return inTimeInSeconds;
	}
	public void setInTimeInSeconds(long inTimeInSeconds) {
		this.inTimeInSeconds = inTimeInSeconds;
	}
	
	
}
