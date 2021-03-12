package com.RestAPI.httpcalls;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class GETBDDAPI {

	
	//Test case 1--Create user and validate status code,body and header--POST
	//             search the created user using booking id--GET call//
	
		@Test
		public void GET_API() {
			
			RestAssured.baseURI="https://restful-booker.herokuapp.com";
			
			int bkid=given().log().all()
			  .contentType(ContentType.JSON)
			  .body(new File("C:\\Users\\RAJ\\eclipse-workspaceNEW\\RestAssuredConcepts\\src\\test\\java\\DataFiles\\Credentials.json"))
			.when().log().all()
			  .post("/booking")
			 .then().log().all()
			  .extract()
			   .path("bookingid");
			
			System.out.println("the bookingid is ::"+bkid);
			
			//now use GET call to retrieve the user created using the variable userid-BKID
			
			RestAssured.baseURI="https://restful-booker.herokuapp.com";
			
			given()
			  .when()
			    .get("booking/"+bkid)
			   .then()
			   .assertThat()
			    .statusCode(200)
			    .and()
			      .body("firstname", equalTo("Jim"));
		
		
			
			  
		}
		
		
		@Test
		public void extract_from_respose_body() {
					
			  //extract the first name from resposne body//
         RestAssured.baseURI="https://restful-booker.herokuapp.com";
			
			
			String respose =	given().log().all()
					  .when().log().all()
					    .get("booking/9")
					   .then().log().all()
					   .assertThat()
					    .statusCode(200)
					    .extract().asString();
		JsonPath js=new JsonPath(respose);
		String frstname=js.get("firstname");
		System.out.println("the first name is ::"+frstname);
				
		
		
			
			
}
}
