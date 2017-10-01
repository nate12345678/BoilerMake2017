package com.hakcathon2017.policedataclarity;

import java.util.ArrayList;

/**
 * Copyright (C) 2017 Nathan Post. All rights reserved.
 * Created by Nathan Post on 9/30/17.
 * This class belongs to the PoliceDataClarity project.
 */

public class Globals {

	volatile static ArrayList<JsonObject> list = new ArrayList<>();
	static double averageDailyHours = 0;
	volatile static int noOfDSPType = 0;

	static String precinct = "";
	static String mainURL = "http://claritybm5.azurewebsites.net/odata/Events?$filter=CadUnit%20eq%20%27PN007%27";
	static String username = "NULL";

  static final String allButSched = "%20and%20Type%20ne%20%27SCHED%27&$orderby=StartTime%20asc";
	static final String allLastWeek = "%20and%20month(StartTime)%20eq%208%20and%20day(StartTime)%20add%207%20gt%2031&$orderby=StartTime%20asc"
	static final String allStat = "$orderby=StartTime%20asc";
	static final String allSched = "%20and%20Type%20eq%20%27SCHED%27&$orderby=StartTime%20asc";
	static final String amountDaysSched = "%20and%20Type%20eq%20%27SCHED%27%20and%20month(StartTime)%20eq%206%20and%20day(StartTime)%20ge%201%20and%20day(StartTime)%20le%207&$orderby=StartTime%20asc";
	static final String lastWeekSched = "%20and%20Type%20eq%20%27SCHED%27%20and%20month(StartTime)%20eq%208%20and%20day(StartTime)%20add%207%20gt%2031&$orderby=StartTime%20asc";
	static final String allDisp = "%20and%20Type%20eq%20%27DSP%27&$orderby=StartTime%20asc";
	static final String amountDaysDisp = "%20and%20Type%20eq%20%27DSP%27%20and%20month(StartTime)%20eq%206%20and%20day(StartTime)%20ge%201%20and%20day(StartTime)%20le%207&$orderby=StartTime%20asc";
	static final String lastWeekDisp = "%20and%20Type%20eq%20%27DSP%27%20and%20month(StartTime)%20eq%208%20and%20day(StartTime)%20add%207%20gt%2031&$orderby=StartTime%20asc";
	static final String allStkDisp = "%20and%20Type%20eq%20%27STKDSP%27&$orderby=StartTime%20asc";
	static final String amountDaysStkDisp = "%20and%20Type%20eq%20%27STKDSP%27%20and%20month(StartTime)%20eq%206%20and%20day(StartTime)%20ge%201%20and%20day(StartTime)%20le%207&$orderby=StartTime%20asc";
	static final String lastWeekStkDisp = "%20and%20Type%20eq%20%27STKDSP%27%20and%20month(StartTime)%20eq%208%20and%20day(StartTime)%20add%207%20gt%2031&$orderby=StartTime%20asc";
	static final String allStops = "%20and%20endswith(Type,%20%27STOP%27)&$orderby=StartTime%20asc";
	static final String amountDaysStops = "%20and%20endswith(Type,%20%27STOP%27)%20and%20month(StartTime)%20eq%206%20and%20day(StartTime)%20ge%201%20and%20day(StartTime)%20le%207&$orderby=StartTime%20asc";
	static final String lastWeekStops = "%20and%20endswith(Type,%20%27STOP%27)%20and%20month(StartTime)%20eq%208%20and%20day(StartTime)%20add%207%20gt%2031&$orderby=StartTime%20asc";
	static final String allShotsF = "%20and%20Code%20eq%20%27SHOTSF%27&$orderby=StartTime%20asc";
	static final String lastWeekShotsF = "%20and%20Code%20eq%20%27SHOTSF%27%20and%20month(StartTime)%20eq%208%20and%20day(StartTime)%20add%207%20gt%2031&$orderby=StartTime%20asc";
	static final String allThreas = "%20and%20Code%20eq%20%27THREAS%27&$orderby=StartTime%20asc";
	static final String lastWeekThreas = "%20and%20Code%20eq%20%27THREAS%27%20and%20month(StartTime)%20eq%208%20and%20day(StartTime)%20add%207%20gt%2031&$orderby=StartTime%20asc";
	static final String allComint = "%20and%20Code%20eq%20%27COMINT%27&$orderby=StartTime%20asc";
	static final String lastWeekComint =  "%20and%20Code%20eq%20%27COMINT%27%20and%20month(StartTime)%20eq%208%20and%20day(StartTime)%20add%207%20gt%2031&$orderby=StartTime%20asc";
	static final String allGn = "%20and%20Code%20eq%20%27GN%27&$orderby=StartTime%20asc";
	static final String lastWeekGn = "%20and%20Code%20eq%20%27GN%27%20and%20month(StartTime)%20eq%208%20and%20day(StartTime)%20add%207%20gt%2031&$orderby=StartTime%20asc";
	}

