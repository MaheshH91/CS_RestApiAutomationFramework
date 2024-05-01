package api.endpoints;

import static io.restassured.RestAssured.*;
import api.payload.UserPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {

	public static Response createUser(UserPayload userPayload) {
		Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(userPayload).when()
				.post(Routes.post_url);

		return response;
	}

	public static Response getUser(String userName) {
		Response response = given().accept(ContentType.JSON).pathParam("username", userName).when().get(Routes.get_url);

		return response;
	}

	public static Response updateUser(String userName, UserPayload userPayload) {
		Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
				.pathParam("username", userName).body(userPayload).when().put(Routes.update_url);

		return response;
	}

	public static Response deleteUser(String userName) {
		Response response = given().accept(ContentType.JSON).pathParam("username", userName).when()
				.delete(Routes.delete_url);

		return response;
	}
}
