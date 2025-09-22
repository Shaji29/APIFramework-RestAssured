package com.api.tests;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.constants.Role;

import io.restassured.module.jsv.JsonSchemaValidator;

import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;

import static io.restassured.RestAssured.*;

public class MasterAPITest {

	@Test
	public void masterAPIresquest() {

		given().baseUri(getProperty("BASE_URI")).header("Authorization", getToken(Role.FD)).contentType("").when()
				.post("/master").then().statusCode(200).time(lessThan(1000L)).body("message", equalTo("Success"))
				.body("data", notNullValue()).body("data", hasKey("mst_oem")).body("data", hasKey("mst_model"))
				.body("$", hasKey("message"))
				.body("data.mst_oem.size()" , equalTo(2))
				.body("data.mst_model.size()" , greaterThan(0))
				.body("data.mst_model.id", everyItem(notNullValue()))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JSONschema/MasterAPIJSONSchema.json"))
				.log().all();

	}
	
	@Test
	public void masterAPIresquestNegative() {

		given().baseUri(getProperty("BASE_URI")).header("Authorization","").contentType("").when()
				.post("/master").then().statusCode(401);
				}

}
