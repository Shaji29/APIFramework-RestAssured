package com.api.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import static com.api.utils.ConfigManager.*;

import com.api.utils.ConfigManager;
import com.pojo.UserCredential;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	@Test
	public void APITest() 	{
		
		
		
		UserCredential usercredential = new UserCredential("iamfd","password");
		given()
		   .baseUri(getProperty("BASE_URI"))
		.and()
		.contentType(ContentType.JSON).accept(ContentType.JSON)
		.body(usercredential)
		.when()
		.post("login")
		.then()
		.statusCode(200)
		.log().all()
		.body("message", equalTo("Success"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JSONschema/FDLoginJSONSchema.json"))
		.time(lessThan(5000L));
		
		System.out.println(ConfigManager.getProperty("BASE_URI"));
		   
	}

}
