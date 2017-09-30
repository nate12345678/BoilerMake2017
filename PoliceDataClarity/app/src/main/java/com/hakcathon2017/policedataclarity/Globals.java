package com.hakcathon2017.policedataclarity;

/**
 * Copyright (C) 2017 Nathan Post. All rights reserved.
 * Created by Nathan Post on 9/30/17.
 * This class belongs to the PoliceDataClarity project.
 */

public class Globals {
	static String username = "NULL";
	static String mainURL = "http://claritybm5.azurewebsites.net/odata/Events?$filter=CadUnit%20eq%20%27PN007%27";

	final String allSched = "%20and%20Type%20eq%20%27SCHED%27&$orderby=StartTime%20asc";
	final String amountDaysSched =
			"%20and%20Type%20eq%20%27SCHED%27%20and%20month(StartTime)%20eq%206%20and%20day(StartTime)%20ge%201%20and%20day(StartTime)%20le%207&$orderby=StartTime%20asc";
	final String allDisp =
			"%20and%20Type%20eq%20%27DSP%27&$orderby=StartTime%20asc";
	final String amountDaysDisp =
			"%20and%20Type%20eq%20%27DSP%27%20and%20month(StartTime)%20eq%206%20and%20day(StartTime)%20ge%201%20and%20day(StartTime)%20le%207&$orderby=StartTime%20asc";
	final String allStkDisp = "%20and%20Type%20eq%20%27STKDSP%27&$orderby=StartTime%20asc";
}
