package api.endpoint;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {
	
	public static Response post_user(User payload)
	{
		Response response = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(routes.post_url);
		
		return response;
	}

	
	public static Response get_user(String username)
	{
		Response response = given()
				.pathParam("username", username)
		.when()
		.get(routes.get_url);
		
		return response;
	}
	
	public static Response update_user(String username, User payload)
	{
		Response response = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.pathParam("username", username)
		.body(payload)
		.when()
		.put(routes.update_url);
		
		return response;
	}
	
	public static Response delete_user(String username)
	{
		Response response = given()
				.pathParam("username", username)
		.when()
		.delete(routes.delete_url);
		
		return response;
	}
}
