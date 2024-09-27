package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoint.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class UserDDT {
	
	@Test (priority =1, dataProvider = "Data",dataProviderClass = DataProviders.class)
	public void createUserDDT (String userID, String userName,String fname,String lname,String useremail,String pwd,String ph)
	{
		User payloadDDT = new User ();
		payloadDDT.setId(Integer.parseInt(userID));
		payloadDDT.setUsername(userName);
		payloadDDT.setFirstName(fname);
		payloadDDT.setLastName(lname);
		payloadDDT.setEmail(useremail);
		payloadDDT.setPassword(pwd);
		payloadDDT.setPhone(ph);
		
		
		Response response = UserEndpoints.post_user(payloadDDT);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	@Test (priority =2, dataProvider = "UserNames",dataProviderClass = DataProviders.class)
	public void deleteUserDDT (String userName)
	{
	
		Response response = UserEndpoints.delete_user(userName);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	

}
