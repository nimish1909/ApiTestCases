package Com.json_server.test;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import Utilities.JsonReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CustomerDetailsApiTest {

	String jsonString = "{\"username\":\"rupeek\", \"password\":password}";

	/*
	 * This method is used to get the all customer details
	 */
	@Test
	public void getCustomerDetails() throws ParseException {
		String host = "https://13.126.80.194:8080";
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);

		Response authentication = RestAssured.given().header("Content-Type", "application/json").body(json).when()
				.post(host).then().log().ifError().statusCode(200).contentType("application/vnd.api+json").extract()
				.response();

		Response resp = RestAssured.given().header("Authorization", "Bearer " + authentication).when()
				.get(host + "/api/v1/users");
		Assert.assertEquals(resp.getStatusCode(), 200);
	}

	/*
	 * This method is used to get the customer details with based on given phone
	 * number
	 */
	@Test
	public void getCustomerPhoneDetails() throws ParseException {
		String host = "https://13.126.80.194:8080";
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);
		// get authentication token
		Response authentication = RestAssured.given().header("Content-Type", "application/json").body(json).when()
				.post(host).then().log().ifError().statusCode(200).contentType("application/vnd.api+json").extract()
				.response();
		Response resp = RestAssured.given().header("Authorization", "Bearer " + authentication).when()
				.get(host + "/api/v1/users");
		Assert.assertEquals(resp.getStatusCode(), 200);
		// parsing josn response
		JsonReader jsonReader = new JsonReader();
		String phone = (String) jsonReader.convertJsonObject(resp.toString()).get("phone");
		String api = "/api/v1/users/" + phone;
		Response CustomerPhoneResponse = RestAssured.given().header("Authorization", "Bearer " + authentication).when()
				.get(host + api);
		Assert.assertEquals(resp.getStatusCode(), 200);
	}
}
