package com.incra

/**
 * The <i>TimeScale</i> entity describes a scale of time such as Day, Month, Quarter, Year
 * 
 * @author Jeffrey Risberg
 * @since 10/19/10
 */
class TimeScale {
	static final int TimeScale_Minute = 1;
	static final int TimeScale_Hour = 2;
	static final int TimeScale_Day = 3;
	static final int TimeScale_WorkWeek = 4;
	static final int TimeScale_Month = 5;
	static final int TimeScale_Quarter = 6;
	static final int TimeScale_Year = 7;
	
	String name
	
	static constraints = {
		name(blank: false, unique: true)
	}
	
	String toString() {
		return name
	}
}
