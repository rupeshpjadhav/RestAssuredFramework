package api.endpoint;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreEndpoints {
	
	// method created for getting URL's from properties file
	static ResourceBundle getURL()
	{
		ResourceBundle routes= ResourceBundle.getBundle("routes"); // Load properties file  // name of the properties file
		return routes;
	}

	
	public static Response post_order(String payloadStore)
	{
		String post_url = getURL().getString("post_url");
		
		Response response = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.when()
		.body(payloadStore)
		.post(post_url);
		
		return response;
	}

	
	public static Response get_orderlist()
	{
		String get_url = getURL().getString("get_url");
		
		Response response = given()
		.when()
		.get(get_url);
		
		return response;
	}
	
	public static Response get_orderbyid(int orderId)
	{
		String getid_url = getURL().getString("getid_url");
		
		Response response = given()
		.accept(ContentType.JSON)
		.pathParam("orderId", orderId)
		.when()
		.get(getid_url);
		
		return response;
	}
	
	public static Response delete_order(int orderId)
	{
		String delete_url = getURL().getString("delete_url");
		Response response = given()
				.pathParam("orderId", orderId)
		.when()
		.delete(delete_url);
		
		return response;
	}
}
