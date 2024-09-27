
package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoint.StoreEndpoints;
import api.endpoint.UserEndpoints;
import api.payload.User;
import api.payload.Store;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class StoreTests {
	
	int orderid = 3;
	@Test (priority =1)
	public void createOrderTest ()
	{
	String payloadStore = "{\r\n"
			+ "  \"id\": "+orderid+",\r\n"
			+ "  \"petId\": 3,\r\n"
			+ "  \"quantity\": 2,\r\n"
			+ "  \"shipDate\": \"2024-04-25T12:47:16.395Z\",\r\n"
			+ "  \"status\": \"placed\",\r\n"
			+ "  \"complete\": true\r\n"
			+ "}";
		
		
		Response response = StoreEndpoints.post_order(payloadStore);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	@Test (priority =2,dependsOnMethods = { "createOrderTest" })
	public void getOrderListTest ()
	{
	
		Response response = StoreEndpoints.get_orderlist();
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test (priority =3,dependsOnMethods = { "getOrderListTest" })
	public void getOrderByIdTest ()
	{
	
		Response response = StoreEndpoints.get_orderbyid(orderid);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test (priority =4,dependsOnMethods = { "getOrderByIdTest" })
	public void deleteOrderTest ()
	{
	
		Response response = StoreEndpoints.delete_order(orderid);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	

}
