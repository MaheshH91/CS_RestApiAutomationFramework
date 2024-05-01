package api.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.UserPayload;
import io.restassured.response.Response;
import net.datafaker.Faker;

public class UserTest {

	Faker faker;
	UserPayload userPayload;
	public static Logger logger;

	@BeforeClass
	public void generateTestData() {
		faker = new Faker();
		userPayload = new UserPayload();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.internet().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());

		// Obtain logger
		logger = LogManager.getLogger("RestAssuredAutomationFramework_Test");
	}

	@Test(priority = 1)
	public void testCreateUser() {
		Response response = UserEndpoints.createUser(userPayload);

		// log response
		System.out.println("Create user data. ");
		response.then().log().all();

		// Validation
		Assert.assertEquals(response.getStatusCode(), 200, "Status code validation");

		logger.info("Create User Executed.");
	}

	@Test(priority = 2)
	public void testGetUser() {
		Response response = UserEndpoints.getUser(this.userPayload.getUsername());

		// log response
		System.out.println("Read user data. ");
		response.then().log().all();

		// Validation
		Assert.assertEquals(response.getStatusCode(), 200, "Status code validation");

		logger.info("Get User Executed.");
	}

	@Test(priority = 3)
	public void testUpdateUser() {
		userPayload.setFirstName(faker.name().firstName());
		Response response = UserEndpoints.updateUser(this.userPayload.getUsername(), userPayload);

		// log response
		System.out.println("Update user data. ");
		response.then().log().all();

		// Validation
		Assert.assertEquals(response.getStatusCode(), 200, "Status code validation");
		// Read user data to check firstname is updated or not
		Response responsePostUpdate = UserEndpoints.getUser(this.userPayload.getUsername());

		System.out.println("After update User data.");
		responsePostUpdate.then().log().all();

		logger.info("Update User Executed.");
	}

	@Test(priority = 4)
	public void testDeleteUser() {
		userPayload.setFirstName(faker.name().firstName());
		Response response = UserEndpoints.deleteUser(this.userPayload.getUsername());

		// log response
		System.out.println("Delete user data. ");
		response.then().log().all();

		// Validation
		Assert.assertEquals(response.getStatusCode(), 200, "Status code validation");

		logger.info("Delete User Executed.");
	}

}
