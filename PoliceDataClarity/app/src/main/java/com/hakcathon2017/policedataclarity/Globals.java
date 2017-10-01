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

	final static String allStat = "$orderby=StartTime%20asc";
	final static String allSched =
			"%20and%20Type%20eq%20%27SCHED%27&$orderby=StartTime%20asc";
	final static String amountDaysSched =
			"%20and%20Type%20eq%20%27SCHED%27%20and%20month(StartTime)%20eq%206%20and%20day(StartTime)%20ge%201%20and%20day(StartTime)%20le%207&$orderby=StartTime%20asc";
	final static String lastWeekSched =
			"%20and%20Type%20eq%20%27SCHED%27%20and%20month(StartTime)%20eq%208%20and%20day(StartTime)%20sub%207%20eq%2022&$orderby=StartTime%20asc";
	final static String allDisp = "%20and%20Type%20eq%20%27DSP%27&$orderby=StartTime%20asc";
	final static String amountDaysDisp =
			"%20and%20Type%20eq%20%27DSP%27%20and%20month(StartTime)%20eq%206%20and%20day(StartTime)%20ge%201%20and%20day(StartTime)%20le%207&$orderby=StartTime%20asc";
	final static String lastWeekDisp =
			"%20and%20Type%20eq%20%27DSP%27%20and%20month(StartTime)%20eq%208%20and%20day(StartTime)%20sub%207%20eq%2022&$orderby=StartTime%20asc";
	final static String allStkDisp = "%20and%20Type%20eq%20%27STKDSP%27&$orderby=StartTime%20asc";
	final static String amountDaysStkDisp =
			"%20and%20Type%20eq%20%27STKDSP%27%20and%20month(StartTime)%20eq%206%20and%20day(StartTime)%20ge%201%20and%20day(StartTime)%20le%207&$orderby=StartTime%20asc";
	final static String lastWeekStkDisp =
			"%20and%20Type%20eq%20%27STKDSP%27%20and%20month(StartTime)%20eq%208%20and%20day(StartTime)%20sub%207%20eq%2022&$orderby=StartTime%20asc";
	final static String allStops = "%20and%20endswith(Type,%20%27STOP%27)&$orderby=StartTime%20asc";
	final static String allLastWeek = "%20and%20day(StartTime)%20sub%207%20ge%2022%20and%20month(StartTime)%20eq%208&$orderby=Id%20asc";
	final static String amountDaysStops =
			"%20and%20endswith(Type,%20%27STOP%27)%20and%20month(StartTime)%20eq%206%20and%20day(StartTime)%20ge%201%20and%20day(StartTime)%20le%207&$orderby=StartTime%20asc";
	final static String lastWeekStops =
			"%20and%20endswith(Type,%20%27STOP%27)%20and%20month(StartTime)%20eq%208%20and%20day(StartTime)%20sub%207%20eq%2022&$orderby=StartTime%20asc";

}
