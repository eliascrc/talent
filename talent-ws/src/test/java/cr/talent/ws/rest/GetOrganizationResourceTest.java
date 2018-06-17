package cr.talent.ws.rest;

import com.jayway.restassured.path.json.JsonPath;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

/**
 * This class will automate tests for the get organization web service.
 * @author Josue Cubero.
 */
public class GetOrganizationResourceTest extends FunctionalTest {

    @Test
    public void pingTest() {
        given().when().get("/").then().statusCode(200);
    }

    @Test
    public void nullBodyRequestTest() {
        given()
                .when().get("/ws/login/organization")
                .then().statusCode(400);
    }

    @Test
    public void emptyUIDRequestTest(){
        given()
                .queryParam("uniqueIdentifier","")
                .when().get("/ws/login/organization")
                .then().statusCode(400);
    }

    @Test
    public void invalidOrganizationRequestTest(){
        given()
                .queryParam("uniqueIdentifier","congo-labs")
                .when().get("/ws/login/organization")
                .then().statusCode(404);
    }

    @Test
    public void validOrganizationRequestTest(){

        JsonPath jsonPath = given()
                .queryParam("uniqueIdentifier","monkey-labs")
                .when().get("/ws/login/organization")
                .then().extract().response().jsonPath();


        String name = "Monkey Labs";
        String uniqueIdentifier =  "monkey-labs";
        String twoStepVerification = "false";

        assertEquals(jsonPath.getString("name"),name);
        assertEquals(jsonPath.getString("uniqueIdentifier"),uniqueIdentifier);
        assertEquals(jsonPath.getString("twoStepVerification"),twoStepVerification);

    }

}