package com.api.utils;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import static com.api.constants.Role.*;

import com.api.constants.Role;
import com.pojo.UserCredential;

import io.restassured.http.ContentType;

public class AuthTokenProvider {

	private AuthTokenProvider() {
	}

	public static String getToken(Role role) {

		UserCredential usercredential = null;

		if (role == FD) {

			usercredential = new UserCredential("iamfd", "password");
		}

		else if (role == SUP) {

			usercredential = new UserCredential("iamsup", "password");
		}
		
		else if (role == ENG) {

			usercredential = new UserCredential("iameng", "password");
		}
		
		else if (role == QC) {

			usercredential = new UserCredential("iamqc", "password");
		}

		String token = given().baseUri("http://64.227.160.186:9000/v1").contentType(ContentType.JSON)
				.body(usercredential).when().post("login").then()

				.log().ifValidationFails().statusCode(200).body("message", equalTo("Success")).extract().body()
				.jsonPath().getString("data.token");

		return token;

	}

}
