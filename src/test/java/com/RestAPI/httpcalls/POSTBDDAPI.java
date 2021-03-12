package com.RestAPI.httpcalls;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class POSTBDDAPI {
	
	//Concept1--Test case to generate token using body as string//
	
	
	@Test
	public void post_API_Auth_Token() {
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";

		
		given().log().all()
		  .contentType(ContentType.JSON)
		  .body("{\r\n" + 
		  		"    \"username\" : \"admin\",\r\n" + 
		  		"    \"password\" : \"password123\"\r\n" + 
		  		"}")
		.when().log().all()
		  .post("/auth")
		 .then().log().all()
		   .assertThat()
		   .statusCode(200);
		  
	}
	
	
	//Concept 2--How to capture the token--using extract and giving the path as token//
	
	@Test
	public void Token_extract() {
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		String tokenid=given().log().all()
		  .contentType(ContentType.JSON)
		  .body("{\r\n" + 
		  		"    \"username\" : \"admin\",\r\n" + 
		  		"    \"password\" : \"password123\"\r\n" + 
		  		"}")
		.when().log().all()
		  .post("/auth")
		 .then().log().all()
		   .extract()
		     .path("token");
		  
		System.out.println("the token id is::"+tokenid);
		Assert.assertNotNull(tokenid);
		//check that token id is generated --means it is not null and returns a value//
	}
	

	
	//Test case 3--Create user and validate status code,body and header//
	
	@Test
	public void post_API_FILEobject() {
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		given().log().all()
		  .contentType(ContentType.JSON)
		  .body(new File("C:\\Users\\RAJ\\eclipse-workspaceNEW\\RestAssuredConcepts\\src\\test\\java\\DataFiles\\Credentials.json"))
		.when().log().all()
		  .post("/booking")
		 .then().log().all()
		   .assertThat()
		   .statusCode(200)
		   .and()
		   .body("booking.lastname", equalTo("Brown"))
		   .and()
		   .header("Server", equalTo("Cowboy"));
		
		  
	}
	
	

}
