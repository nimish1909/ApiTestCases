package Com.json_server.test;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Createdata {
	
	
	
	String jsonString = "{\"username\":\"rupeek\", \"password\":password}";
	/*
	 * This  method is used to get the all customer details
	*/
	@Test
	public void getCustomerDetails() throws ParseException
	{
		
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);
	
	Response authentication =  
	        RestAssured.given().
	            header("Content-Type", "application/json").
	            body(json).
	        when().
	            post("https://13.126.80.194:8080").
	        then().
	            log().ifError().
	            statusCode(200).
	            contentType("application/vnd.api+json").
	        extract().response();
	
	Response resp =RestAssured.given().header("Authorization", "Bearer "+authentication).when().get("http://api/v1/users");
	Assert.assertEquals(resp.getStatusCode(),200);
	
}
	/*
	 * This  method is used to get the customer details with based on given phone number
	*/
	@Test
	public void getCustomerPhoneDetails() throws ParseException
	{
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);
	
	Response authentication =  
	        RestAssured.given().
	            header("Content-Type", "application/json").
	            body(json).
	        when().
	            post("https://13.126.80.194:8080").
	        then().
	            log().ifError().
	            statusCode(200).
	            contentType("application/vnd.api+json").
	        extract().response();
	Response resp =RestAssured.given().header("Authorization", "Bearer "+authentication).when().get("http://api/v1/users");
	Assert.assertEquals(resp.getStatusCode(),200);
	// parsing josn response 
    Object obj = new JSONParser().parse(resp.toString()); 
      
    // type casting obj to JSONObject 
    JSONObject jo = (JSONObject) obj; 
    String phone = (String) jo.get("phone");
    
    String api="http://api/v1/users/"+phone;
    Response CustomerPhoneResponse =RestAssured.given().header("Authorization", "Bearer "+authentication).when().get(api);
    Assert.assertEquals(resp.getStatusCode(),200);
}
}
