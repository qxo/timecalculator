package net.sf.jiffie;

public class WeekTime {
	private String week;
	private long timeInSeconds;
	private boolean hoursShortfall;
	private float leavesCut;
	
	public WeekTime(String week, long timeInSeconds) {
		super();
		this.week = week;
		this.timeInSeconds = timeInSeconds;
		int hours;
		
		if (timeInSeconds < 35*60*60)
		{
			hoursShortfall = true;
		}
		
		if (hoursShortfall)
		{
			hours = (int)((35*60*60 - timeInSeconds)/3600);
			leavesCut = hours / 8;
		}
	}

	public boolean isHoursShortfall() {
		return hoursShortfall;
	}

	public void setHoursShortfall(boolean hoursShortfall) {
		this.hoursShortfall = hoursShortfall;
	}

	public float getLeavesCut() {
		return leavesCut;
	}

	public void setLeavesCut(float leavesCut) {
		this.leavesCut = leavesCut;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public long getTimeInSeconds() {
		return timeInSeconds;
	}

	public void setTimeInSeconds(long timeInSeconds) {
		this.timeInSeconds = timeInSeconds;
	}
	
	
	
}
