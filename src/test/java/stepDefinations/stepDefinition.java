package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class stepDefinition extends Utils {
	// Make request and response Specification at class level as global to access it
	// for all the methods in the class
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	JsonPath js;
	static String place_id;
	Utils request = new Utils();

//	@Given("Add Place Payload")
//	public void add_place_payload() throws IOException {
//		
//		resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
//		res=given().spec(requestSpecification()).body(data.addPlacePayload());

	@Given("Add Place Payload {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {
		res = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));
	}

//	@When("User calls {string} with post http request")
//	public void user_calls_with_post_http_request(String string) {
//	response=res.when().post("/maps/api/place/add/json").then().spec(resspec).extract().response();
	@When("User calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource, String method) {
		// Constructor will be called with value of resource which is passed
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (method.equalsIgnoreCase("Post")) {
			response = res.when().post(resourceAPI.getResource());
			// then().spec(resspec).extract().response();
		} else if (method.equalsIgnoreCase("Get")) {

			response = res.when().get(resourceAPI.getResource());
		}

	}

	@Then("the API call is successful with success code {int}")
	public void the_api_call_is_successful_with_success_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String KeyValue, String ExpectedValue) {
		String resp = response.asString();
		js = new JsonPath(resp);

		assertEquals(getJsonPath(response, KeyValue).toString(), ExpectedValue);
	}

	@Then("verify if the place_id created maps to {string} using {string}")
	public void verify_if_the_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		// Write code here that turns the phrase above into concrete actions

		// prepare request spec
		// place_id=js.get("place_id");
		place_id = getJsonPath(response, "place_id");
		// Specbuilder for getting place_id which we get from hitting the api while post
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_post_http_request(resource, "Get");

		// Get name
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName, expectedName);
	}
	// Delete place API

	@Given("Delete Place Payload")
	public void delete_place_payload() throws IOException {
		// Write code here that turns the phrase above into concrete actions
		res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}

}
