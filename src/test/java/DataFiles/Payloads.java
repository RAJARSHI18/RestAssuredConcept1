package DataFiles;

public class Payloads {

	
	public static String Addplace(String frstname,String lstname) {
		
		
		return ("{\r\n" + 
				"    \"firstname\" : \""+frstname+"\",\r\n" + 
				"    \"lastname\" : \""+lstname+"\",\r\n" + 
				"    \"totalprice\" : 120,\r\n" + 
				"    \"depositpaid\" : true,\r\n" + 
				"    \"bookingdates\" : {\r\n" + 
				"        \"checkin\" : \"2018-01-01\",\r\n" + 
				"        \"checkout\" : \"2019-01-01\"\r\n" + 
				"    },\r\n" + 
				"    \"additionalneeds\" : \"Breakfast\"\r\n" + 
				"}");
		
		
		
		
		
		
		
	}
}
