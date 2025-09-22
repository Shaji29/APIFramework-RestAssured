package com.api.tests;

import org.checkerframework.checker.index.qual.LessThan;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;


import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static io.restassured.RestAssured.*;

public class CountAPITest {

	@Test
	public void getJobCount() {

		given().baseUri(ConfigManager.getProperty("BASE_URI"))
				.header("Authorization", AuthTokenProvider.getToken(Role.FD)).when().get("/dashboard/count").then()
				.log().all().statusCode(200).body("message", equalTo("Success")).
				time(lessThan(500L))
				.body("data", notNullValue())
				.body("data.size()", equalTo(3))
				.body("data.count", everyItem(greaterThanOrEqualTo(0)))
			.body("data.label", everyItem(not(blankOrNullString())))
				.body(matchesJsonSchemaInClasspath("JSONschema/JobCountSchema.json"));
	    }
	
}
