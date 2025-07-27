package api.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.userEndPoints;
import api.payload.user;
import io.restassured.response.Response;

public class userTest {
	Faker faker;
	user userPayload;
	public static Logger logger;
	
	@BeforeClass
	public void generateTestData() {
		faker =new Faker();
		userPayload = new user();
		
		userPayload.setId(faker.idNumber().hashCode());	
		userPayload.setUsername(faker.name().username());	
		userPayload.setFirstName(faker.name().firstName());	
		userPayload.setLastName(faker.name().lastName());	
		userPayload.setEmail(faker.internet().safeEmailAddress());	
		userPayload.setPassword(faker.internet().password());	
		userPayload.setPhone(faker.phoneNumber().cellPhone());	
		
		logger = LogManager.getLogger("RestAssuredFramework");
		}

	
	@Test(priority=1)
	public void testCreateUser() {
		Response response =userEndPoints.createUser(userPayload);
		System.out.println("printing logs for the Createuser");
		//logs
		response.then().log().all();
		
		//validation
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//log
		logger.info("Create User is executed");
	}
	
	@Test(priority=2)
	public void testGetUser() {
		Response response =userEndPoints.GetUser(this.userPayload.getUsername());
		System.out.println("printing logs for the Getuser");
		//logs
		response.then().log().all();
		
		//validation
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//log
		logger.info("Get User is executed");
	}
	
	@Test(priority=3)
	public void testupdateUser() {
		userPayload.setFirstName(faker.name().firstName());	
		Response response =userEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
		System.out.println("printing logs for the updateuser");
		//logs
		response.then().log().all();
		
		//log
		logger.info("Update User is executed");
		
		//validation
		Assert.assertEquals(response.getStatusCode(), 200);
		
		Response response1 =userEndPoints.GetUser(this.userPayload.getUsername());
		System.out.println("printing the logs after the update of firstname");
		response1.then().log().all();
		
	}
	
	@Test(priority=4)
	public void testdeleteUser() {
		Response response =userEndPoints.deleteUser(this.userPayload.getUsername());
		System.out.println("printing logs for the deleteuser");
		//logs
		response.then().log().all();
		
		//validation
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//log
		logger.info("Delete User is executed");
	}
}
