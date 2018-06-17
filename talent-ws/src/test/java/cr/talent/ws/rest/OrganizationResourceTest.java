package cr.talent.ws.rest;

import com.jayway.restassured.http.ContentType;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

/**
 * This class will automate tests for the create organization web service.
 * @author Josue Cubero.
 */
public class OrganizationResourceTest extends FunctionalTest {


    @Test
    public void pingTest() {
        given().when().get("/").then().statusCode(200);
    }

    @Test
    public void noHeaderRequestTest() {
        given()
                .when().post("/ws/organization/create")
                .then().statusCode(400);
    }

    @Test
    public void nullBodyRequestTest() {
        given()
                .contentType(ContentType.URLENC)
                .when().post("/ws/organization/create")
                .then().statusCode(400);
    }

    @Test
    public void emptyNameRequestTest(){
        given()
                .contentType(ContentType.URLENC)
                .formParam("uniqueIdentifier","congo-labs")
                .formParam("name","")
                .formParam("username", "john.tolkien@example.com")
                .formParam("termsOfServiceAccepted",true)
                .when().post("/ws/organization/create")
                .then().statusCode(400);
    }

    @Test
    public void emptyUIDRequestTest(){
        given()
                .contentType(ContentType.URLENC)
                .formParam("uniqueIdentifier","")
                .formParam("name","Congo Labs")
                .formParam("username", "john.tolkien@example.com")
                .formParam("termsOfServiceAccepted",true)
                .when().post("/ws/organization/create")
                .then().statusCode(400);
    }

    @Test
    public void emptyUsernameRequestTest(){
        given()
                .contentType(ContentType.URLENC)
                .formParam("uniqueIdentifier","congo-labs")
                .formParam("name","Congo Labs")
                .formParam("username", "")
                .formParam("termsOfServiceAccepted",true)
                .when().post("/ws/organization/create")
                .then().statusCode(400);
    }

    @Test
    public void noToSAcceptedRequestTest(){
        given()
                .contentType(ContentType.URLENC)
                .formParam("uniqueIdentifier","congo-labs")
                .formParam("name","Congo Labs")
                .formParam("username", "john.tolkien@example.com")
                .formParam("termsOfServiceAccepted","")
                .when().post("/ws/organization/create")
                .then().statusCode(400);
    }

    @Test
    public void emptyParamsRequestTest(){
        given()
                .contentType(ContentType.URLENC)
                .formParam("uniqueIdentifier","")
                .formParam("name","")
                .formParam("username", "")
                .formParam("termsOfServiceAccepted","")
                .when().post("/ws/organization/create")
                .then().statusCode(400);
    }

    @Test
    public void existingOrganizationRequestTest(){
        given()
                .contentType(ContentType.URLENC)
                .formParam("uniqueIdentifier","monkey-labs")
                .formParam("name","Monkey Labs")
                .formParam("username", "john.tolkien@example.com")
                .formParam("termsOfServiceAccepted",true)
                .when().post("/ws/organization/create")
                .then().statusCode(409);
    }

    /*@Test
    public void createValidOrganizationRequestTest(){
        given()
                .contentType(ContentType.URLENC)
                .formParam("uniqueIdentifier","mainieris-company")
                .formParam("name","Mainieri´s company")
                .formParam("username", "john.tolkien@example.com")
                .formParam("termsOfServiceAccepted",true)
                .when().post("/ws/organization/create")
                .then().statusCode(200);
    }*/

}