package com.hakcathon2017.policedataclarity;

/**
 * Copyright (C) 2017 Nathan Post. All rights reserved.
 * Created by Nathan Post on 9/30/17.
 * This class belongs to the PoliceDataClarity project.
 */

public class Globals {
	static String username = "NULL";
	static final String allStat = "$orderby=StartTime%20asc";
	static final String allSched = "%20and%20Type%20eq%20%27SCHED%27&$orderby=StartTime%20asc";
	static final String amountDaysSched = "%20and%20Type%20eq%20%27SCHED%27%20and%20month(StartTime)%20eq%206%20and%20day(StartTime)%20ge%201%20and%20day(StartTime)%20le%207&$orderby=StartTime%20asc";
	static final String lastWeekSched = "%20and%20Type%20eq%20%27SCHED%27%20and%20month(StartTime)%20eq%208%20and%20day(StartTime)%20add%207%20ge%2031&$orderby=StartTime%20asc";
	static final String allDisp = "%20and%20Type%20eq%20%27DSP%27&$orderby=StartTime%20asc";
	static final String amountDaysDisp = "%20and%20Type%20eq%20%27DSP%27%20and%20month(StartTime)%20eq%206%20and%20day(StartTime)%20ge%201%20and%20day(StartTime)%20le%207&$orderby=StartTime%20asc";
	static final String lastWeekDisp = "%20and%20Type%20eq%20%27DSP%27%20and%20month(StartTime)%20eq%208%20and%20day(StartTime)%20add%207%20ge%2031&$orderby=StartTime%20asc";
	static final String allStkDisp = "%20and%20Type%20eq%20%27STKDSP%27&$orderby=StartTime%20asc";
	static final String amountDaysStkDisp = "%20and%20Type%20eq%20%27STKDSP%27%20and%20month(StartTime)%20eq%206%20and%20day(StartTime)%20ge%201%20and%20day(StartTime)%20le%207&$orderby=StartTime%20asc";
	static final String lastWeekStkDisp = "%20and%20Type%20eq%20%27STKDSP%27%20and%20month(StartTime)%20eq%208%20and%20day(StartTime)%20add%207%20ge%2031&$orderby=StartTime%20asc";
	static final String allStops = "%20and%20endswith(Type,%20%27STOP%27)&$orderby=StartTime%20asc";
	static final String amountDaysStops = "%20and%20endswith(Type,%20%27STOP%27)%20and%20month(StartTime)%20eq%206%20and%20day(StartTime)%20ge%201%20and%20day(StartTime)%20le%207&$orderby=StartTime%20asc";
	static final String lastWeekStops = "%20and%20endswith(Type,%20%27STOP%27)%20and%20month(StartTime)%20eq%208%20and%20day(StartTime)%20add%207%20ge%2031&$orderby=StartTime%20asc";
}
