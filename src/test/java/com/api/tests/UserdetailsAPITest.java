package com.api.tests;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import static com.api.utils.AuthTokenProvider.*;

import static com.api.constants.Role.*;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

public class UserdetailsAPITest {

@Test	
	public static void UserdetailAPI() {
	
	
	
	Header authHeader = new Header("Authorization",getToken(QC));
	given().baseUri(ConfigManager.getProperty("BASE_URI")).
	and()
	.header(authHeader)
	.accept(ContentType.JSON)
	.when()
	.get("userdetails")
	.then()
	.statusCode(200)
	.time(lessThan(1000L))
	.log().all()
	.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JSONschema/FDUserDetails.json"))
	.extract().response();
	


	}
}
