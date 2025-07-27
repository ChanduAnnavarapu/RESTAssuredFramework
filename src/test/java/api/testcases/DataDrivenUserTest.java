package api.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.userEndPoints;
import api.payload.user;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenUserTest {
	
	
	@Test(priority=1,dataProvider="AllData",dataProviderClass=DataProviders.class)
	public void testCreateUser(String userId,String Username,String Fname,String Lname,String Email,String password,String phone) {
		
		user userPayload=new user();
		
		userPayload.setId(Integer.parseInt(userId));
		userPayload.setUsername(Username);
		userPayload.setFirstName(Fname);
		userPayload.setLastName(Lname);
		userPayload.setEmail(Email);
		userPayload.setPassword(password);
		userPayload.setPhone(phone);
		Response response =userEndPoints.createUser(userPayload);
		System.out.println("printing logs for the Createuser");
		//logs
		response.then().log().all();
		
		//validation
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	@Test(priority=2,dataProvider="userNameData",dataProviderClass=DataProviders.class)
	public void testdeleteUser(String username) {
		Response response =userEndPoints.deleteUser(username);
		System.out.println("printing logs for the deleteuser");
		//logs
		response.then().log().all();
		
		//validation
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
}
