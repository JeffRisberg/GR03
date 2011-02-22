fixture {
	tsMinute  (com.incra.TimeScale, scale: 1, name: "Minute")
	tsHour    (com.incra.TimeScale, scale: 2, name: "Hour")
	tsDay     (com.incra.TimeScale, scale: 3, name: "Day")
	tsWorkWeek(com.incra.TimeScale, scale: 4, name: "WorkWeek")
	tsMonth   (com.incra.TimeScale, scale: 5, name: "Month")
	tsQuarter (com.incra.TimeScale, scale: 6, name: "Quarter")
	tsYear    (com.incra.TimeScale, scale: 7, name: "Year")
	
	ts01(com.incra.TimeSegment, label: "2008", timeScale: tsYear, fromDate: new Date(108,0,1), toDate: new Date(108,11,31,23,59,59))
	ts02(com.incra.TimeSegment, label: "2009", timeScale: tsYear, fromDate: new Date(109,0,1), toDate: new Date(109,11,31,23,59,59))
	ts03(com.incra.TimeSegment, label: "2010", timeScale: tsYear, fromDate: new Date(110,0,1), toDate: new Date(110,11,31,23,59,59))
	ts04(com.incra.TimeSegment, label: "2011", timeScale: tsYear, fromDate: new Date(111,0,1), toDate: new Date(111,11,31,23,59,59))
	ts05(com.incra.TimeSegment, label: "2012", timeScale: tsYear, fromDate: new Date(112,0,1), toDate: new Date(112,11,31,23,59,59))
	ts06(com.incra.TimeSegment, label: "2013", timeScale: tsYear, fromDate: new Date(113,0,1), toDate: new Date(113,11,31,23,59,59))
	ts07(com.incra.TimeSegment, label: "1Q 2010", timeScale: tsQuarter, fromDate: new Date(110,0,1), toDate: new Date(110,2,31,23,59,59))
	ts08(com.incra.TimeSegment, label: "2Q 2010", timeScale: tsQuarter, fromDate: new Date(110,3,1), toDate: new Date(110,5,30,23,59,59))
	ts09(com.incra.TimeSegment, label: "3Q 2010", timeScale: tsQuarter, fromDate: new Date(110,6,1), toDate: new Date(110,8,30,23,59,59))
	ts10(com.incra.TimeSegment, label: "4Q 2010", timeScale: tsQuarter, fromDate: new Date(110,9,1), toDate: new Date(110,11,31,23,59,59))
	ts11(com.incra.TimeSegment, label: "1Q 2011", timeScale: tsQuarter, fromDate: new Date(111,0,1), toDate: new Date(111,2,31,23,59,59))
	ts12(com.incra.TimeSegment, label: "2Q 2011", timeScale: tsQuarter, fromDate: new Date(111,3,1), toDate: new Date(111,5,30,23,59,59))
	ts13(com.incra.TimeSegment, label: "3Q 2011", timeScale: tsQuarter, fromDate: new Date(111,6,1), toDate: new Date(111,8,30,23,59,59))
	ts14(com.incra.TimeSegment, label: "4Q 2011", timeScale: tsQuarter, fromDate: new Date(111,9,1), toDate: new Date(111,11,31,23,59,59))
}