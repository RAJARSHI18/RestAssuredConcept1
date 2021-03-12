package com.RestAPI.httpcalls;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import DataFiles.Payloads;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

public class DataDriven {
	
	@DataProvider(name="test_data_addplaceapi")
	public Object[][] test_data_addplaceapi(){
		
		return new Object[][]
				{
						{"jack","sparrow"},
						{"jack123","sparrow123"}
				};
		
		
	}
	
	
	@Test(dataProvider ="test_data_addplaceapi")
	public void Data_driven(String frstname,String lstname) {
		
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		given().log().all()
		 .contentType(ContentType.JSON)
		    .body(Payloads.Addplace(frstname,lstname))
		    .when().log().all()
		      .post("/booking")
		      .then().log().all()
		      .assertThat()
		      .statusCode(200)
		  ;
		
		
	}

}
