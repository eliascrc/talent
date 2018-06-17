package cr.talent.ws.rest;

import com.jayway.restassured.filter.session.SessionFilter;
import com.jayway.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

/**
 * This class will automate tests for the organization capability web service.
 * @author Josue Cubero.
 */
public class OrganizationCapabilityResourceTest extends FunctionalTest {

    private final String createOrganizationWebService = "/ws/organization/capability/create";
    private final String logInWebService = "/ws/login";
    private final String organizationUniqueIdentifier = "organizationUniqueIdentifier";
    private final String name = "name";
    private SessionFilter sessionFilter;

    public OrganizationCapabilityResourceTest(){
        this.sessionFilter = new SessionFilter();
    }

    @Before
    public void logInUserForTests(){

        given()
                .formParam("username","jo96cube@gmail.com")
                .formParam("password","Talent.123")
                .formParam("organizationIdentifier","monkey-labs")
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .post(this.logInWebService)
                .then().statusCode(302);

    }

    @Test
    public void pingTest() {
        given().when().get("/").then().statusCode(200);
    }

    @Test
    public void noAuthRequestTest() {
        given()
                .when().post(this.createOrganizationWebService)
                .then().statusCode(401);
    }

    @Test
    public void noHeaderRequestTest() {
        given()
                .filter(this.sessionFilter)
                .when().post(this.createOrganizationWebService)
                .then().statusCode(400);
    }

    @Test
    public void nullBodyRequestTest() {
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .when().post(this.createOrganizationWebService)
                .then().statusCode(400);
    }

    @Test
    public void onlyEmptyNameRequestTest(){
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam(this.name,"")
                .when().post(this.createOrganizationWebService)
                .then().statusCode(400);
    }

    @Test
    public void onlyEmptyUIDRequestTest(){
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam(this.organizationUniqueIdentifier,"")
                .when().post(this.createOrganizationWebService)
                .then().statusCode(400);
    }

    @Test
    public void emptyNameRequestTest(){
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam(this.name,"")
                .formParam(this.organizationUniqueIdentifier,"monkey-labs")
                .when().post(this.createOrganizationWebService)
                .then().statusCode(400);
    }

    @Test
    public void emptyUIDRequestTest(){
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam(this.name,"Apache Hive Data Analyst")
                .formParam(this.organizationUniqueIdentifier,"")
                .when().post(this.createOrganizationWebService)
                .then().statusCode(400);
    }

    @Test
    public void emptyParamsRequestTest(){
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam(this.organizationUniqueIdentifier,"")
                .formParam(this.name,"")
                .when().post(this.createOrganizationWebService)
                .then().statusCode(400);
    }

    @Test
    public void invalidOrganizationRequestTest(){
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam(this.organizationUniqueIdentifier,"congo-labs")
                .formParam(this.name,"Apache Hive Data Analyst")
                .when().post(this.createOrganizationWebService)
                .then().statusCode(404);
    }

    @Test
    public void existingCapabilityRequestTest(){
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam(this.organizationUniqueIdentifier,"monkey-labs")
                .formParam(this.name,"Mobile Developer")
                .when().post(this.createOrganizationWebService)
                .then().statusCode(409);
    }

    //This test is commented because once it creates the capability, the next executions will result in a conflict, such as the previous test.
    /*@Test
    public void createCapabilityRequestTest(){
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam(this.organizationUniqueIdentifier,"monkey-labs")
                .formParam(this.name,"QA Analyst")
                .when().post(this.createOrganizationWebService)
                .then().statusCode(200);
    }*/
}