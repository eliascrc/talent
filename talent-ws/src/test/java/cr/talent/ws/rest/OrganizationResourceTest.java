package cr.talent.ws.rest;

import com.jayway.restassured.http.ContentType;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

/**
 * This class will automate tests for the create organization web service.
 * @author Josue Cubero.
 */
public class OrganizationResourceTest extends FunctionalTest {

    private final String createOrganizationWebService = "/ws/organization/create";
    private final String uniqueIdentifier = "uniqueIdentifier";
    private final String name = "name";
    private final String username = "username";
    private final String termsOfServiceAccepted = "termsOfServiceAccepted";

    @Test
    public void pingTest() {
        given().when().get("/").then().statusCode(200);
    }

    @Test
    public void noHeaderRequestTest() {
        given()
                .when().post(this.createOrganizationWebService)
                .then().statusCode(400);
    }

    @Test
    public void nullBodyRequestTest() {
        given()
                .contentType(ContentType.URLENC)
                .when().post(this.createOrganizationWebService)
                .then().statusCode(400);
    }

    @Test
    public void emptyNameRequestTest(){
        given()
                .contentType(ContentType.URLENC)
                .formParam(this.uniqueIdentifier,"congo-labs")
                .formParam(this.name,"")
                .formParam(this.username, "john.tolkien@example.com")
                .formParam(this.termsOfServiceAccepted,true)
                .when().post(this.createOrganizationWebService)
                .then().statusCode(400);
    }

    @Test
    public void emptyUIDRequestTest(){
        given()
                .contentType(ContentType.URLENC)
                .formParam(this.uniqueIdentifier,"")
                .formParam(this.name,"Congo Labs")
                .formParam(this.username, "john.tolkien@example.com")
                .formParam(this.termsOfServiceAccepted,true)
                .when().post(this.createOrganizationWebService)
                .then().statusCode(400);
    }

    @Test
    public void emptyUsernameRequestTest(){
        given()
                .contentType(ContentType.URLENC)
                .formParam(this.uniqueIdentifier,"congo-labs")
                .formParam(this.name,"Congo Labs")
                .formParam(this.username, "")
                .formParam(this.termsOfServiceAccepted,true)
                .when().post(this.createOrganizationWebService)
                .then().statusCode(400);
    }

    @Test
    public void noToSAcceptedRequestTest(){
        given()
                .contentType(ContentType.URLENC)
                .formParam(this.uniqueIdentifier,"congo-labs")
                .formParam(this.name,"Congo Labs")
                .formParam(this.username, "john.tolkien@example.com")
                .formParam(this.termsOfServiceAccepted,"")
                .when().post(this.createOrganizationWebService)
                .then().statusCode(400);
    }

    @Test
    public void emptyParamsRequestTest(){
        given()
                .contentType(ContentType.URLENC)
                .formParam(this.uniqueIdentifier,"")
                .formParam(this.name,"")
                .formParam(this.username, "")
                .formParam(this.termsOfServiceAccepted,"")
                .when().post(this.createOrganizationWebService)
                .then().statusCode(400);
    }

    @Test
    public void existingOrganizationRequestTest(){
        given()
                .contentType(ContentType.URLENC)
                .formParam(this.uniqueIdentifier,"monkey-labs")
                .formParam(this.name,"Monkey Labs")
                .formParam(this.username, "john.tolkien@example.com")
                .formParam(this.termsOfServiceAccepted,true)
                .when().post(this.createOrganizationWebService)
                .then().statusCode(409);
    }

    //This test is commented because once it creates the organization, the next executions will result in a conflict, such as the previous test.
    /*@Test
    public void createValidOrganizationRequestTest(){
        given()
                .contentType(ContentType.URLENC)
                .formParam(this.uniqueIdentifier,"mainieris-company")
                .formParam(this.name,"Mainieri´s company")
                .formParam(this.username, "john.tolkien@example.com")
                .formParam(this.termsOfServiceAccepted,true)
                .when().post(this.createOrganizationWebService)
                .then().statusCode(200);
    }*/

}