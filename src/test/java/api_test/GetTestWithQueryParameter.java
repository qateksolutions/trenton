package api_test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GetTestWithQueryParameter {
    private static final Logger LOGGER = LogManager.getLogger(GetTestWithQueryParameter.class);

    @Test
    public void getUserWithQueryParameter() {
        LOGGER.info("-----API Test: Get All Users with Query Parameter");
        RestAssured.baseURI = "https://reqres.in/api/users";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.queryParam("page", "2").request(Method.GET);
        LOGGER.debug(response.getBody().asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200);

        JsonPath jsonPath = response.jsonPath();
        List<String> listOfEmail = jsonPath.get("data.email");

        String expectedEmail = "michael.lawson@reqres.in";
        boolean emailExist = listOfEmail.contains(expectedEmail);
        Assert.assertTrue(emailExist, expectedEmail + " does not exist");

        LOGGER.info("-----End Test: Get All Users with Query Parameter");
    }
}
