package com.hakcathon2017.policedataclarity;

/**
 * Created by Pavitra on 30-Sep-17.
 */

public class JsonObject {

	String Id;
	String CadUnit;
	String OrgUnit;
	String StartTime;
	String EndTime;
	String Type;
	String Code;
	String Descr;

	public JsonObject(String Id, String CadUnit, String OrgUnit, String StartTime, String EndTime, String Type, String Code, String Descr) {
		this.Id = Id;
		this.CadUnit = CadUnit;
		this.OrgUnit = OrgUnit;
		this.StartTime = StartTime;
		this.EndTime = EndTime;
		this.Type = Type;
		this.Code = Code;
		this.Descr = Descr;

	}

}
