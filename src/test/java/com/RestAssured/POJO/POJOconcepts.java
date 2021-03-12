package com.RestAssured.POJO;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class POJOconcepts {

	
	@Test
	public void AddPlace_usingPojo() throws JsonProcessingException {
		
		//1.Create an object of the pojo class and set the values with set methods//
		//2.SERIALISATION--convert java object into request payload in json format//
		AddPlace place = new AddPlace();
		
	    place.setAccuracy(30);
		place.setAddress("Raj house at toronto");
		place.setLanguage("ENG");
		
		Locationpojo l=new Locationpojo();
		l.setLat(-33.34);
		l.setLng(-34.24);
		place.setLocation(l);
		
		place.setPhone_number("+188383992");
		place.setName("Rajarshi house");
		
		List<String> mylist = new ArrayList<String>();
		mylist.add("shoepark");
		mylist.add("missisauga");
		place.setTypes(mylist);
		
		place.setWebsite("www.raj.com");
		
		//now convert the pojo java class to json--SERIALIZATION
		//API used here to convert is JACKSON API
		
		ObjectMapper mapper= new ObjectMapper();
		String addplacejson = mapper.writeValueAsString(place);
		
		System.out.println(addplacejson);
		
		//Now use this addplacejson variable in the api code body //
		
		//POST --API for add place
		
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		
		given().log().all()
		 .queryParam("key", "qaclick123")
		 .header("Content-Type", "application/json")
		     .body(addplacejson)
		  .when().log().all()
		   .post("/maps/api/place/add/json")
		   .then().log().all()
		     .assertThat()
		      .statusCode(200)
		      .and()
		     .header("server", equalTo("Apache/2.4.18 (Ubuntu)"))
		       ;
		

		
		
		
		
		
	}
}
