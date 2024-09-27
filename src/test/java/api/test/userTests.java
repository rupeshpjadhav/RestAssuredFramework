package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoint.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class userTests {
	Faker faker;
	User userPayload;
	@BeforeClass
	public void SetupData () {
		
		faker = new Faker();
		userPayload = new User();		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		userPayload.setUsername(faker.name().username());
	}
	
	@Test(priority = 1)
	public void CreateUserTest ()
	{
		Response response = UserEndpoints.post_user(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority =2)
	public void ReadUserTest () {
		Response response = UserEndpoints.get_user(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 3)
	public void UpdateUserTest () {
        // modify some user properties
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		
		Response response = UserEndpoints.update_user(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		Response readresponse = UserEndpoints.get_user(this.userPayload.getUsername());
		readresponse.then().log().all();
		Assert.assertEquals(readresponse.getStatusCode(), 200);
	}
	
	@Test(priority =4)
	public void DeleteUserTest () {
		Response response = UserEndpoints.delete_user(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
