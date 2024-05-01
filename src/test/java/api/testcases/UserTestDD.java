package api.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.UserPayload;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class UserTestDD {

    UserPayload userPayload;

    @Test(priority = 1, dataProvider = "AllData", dataProviderClass = DataProviders.class)
    public void testCreateUser(String userId, String userName, String firstName, String lastName, String emailId, String password, String phoneNum) {
        try {
            // Create user payload
            userPayload = new UserPayload();
            userPayload.setId(Integer.parseInt(userId));
            userPayload.setUsername(userName);
            userPayload.setFirstName(firstName);
            userPayload.setLastName(lastName);
            userPayload.setEmail(emailId);
            userPayload.setPassword(password);
            userPayload.setPhone(phoneNum);

            // Call API to create user
            Response response = UserEndpoints.createUser(userPayload);

            // Log response
            System.out.println("Create user data:");
            response.then().log().all();

            // Validation
            Assert.assertEquals(response.getStatusCode(), 200, "Status code validation");
        } catch (NumberFormatException e) {
            // Handle parsing error
            Assert.fail("Failed to parse userId to integer: " + e.getMessage());
        } catch (Exception e) {
            // Handle other exceptions
            Assert.fail("An unexpected error occurred: " + e.getMessage());
        }
    }
    @Test(priority = 2, dataProvider = "UserNamesData", dataProviderClass = DataProviders.class)
	public void testGetUser(String username) {
		Response response = UserEndpoints.getUser(username);

		// log response
		System.out.println("Fetch user data. ");
		response.then().log().all();

		// Validation
		Assert.assertEquals(response.getStatusCode(), 200, "Status code validation");
	}

    @Test(priority = 3, dataProvider = "UserNamesData", dataProviderClass = DataProviders.class)
	public void testDeleteUser(String username) {
		Response response = UserEndpoints.deleteUser(username);

		// log response
		System.out.println("Delete user data. ");
		response.then().log().all();

		// Validation
		Assert.assertEquals(response.getStatusCode(), 200, "Status code validation");
	}

}
