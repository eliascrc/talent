package cr.talent.ws.rest;

import com.jayway.restassured.filter.session.SessionFilter;
import com.jayway.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

/**
 * This class will automate tests for the create project web service.
 * @author Josue Cubero.
 */
public class ProjectResourceTest extends FunctionalTest {

    private final String createProjectWebService = "/ws/organization/project/create";
    private final String name = "name";
    private final String organizationUniqueIdentifier = "organizationUniqueIdentifier";
    private SessionFilter sessionFilter;

    public ProjectResourceTest(){
        this.sessionFilter = new SessionFilter();
    }

    @Before
    public void logInUserForTests(){

        given()
                .formParam("username","daniel.montesdeocab@gmail.com")
                .formParam("password","Talent.123")
                .formParam("organizationIdentifier","monkey-labs")
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .post("/ws/login")
                .then().statusCode(302);

    }

    @Test
    public void pingTest() {
        given().when().get("/").then().statusCode(200);
    }

    @Test
    public void noAuthRequestTest() {
        given()
                .when().post(this.createProjectWebService)
                .then().statusCode(401);
    }

    @Test
    public void noHeaderRequestTest() {
        given()
                .filter(this.sessionFilter)
                .when().post(this.createProjectWebService)
                .then().statusCode(400);
    }

    @Test
    public void nullBodyRequestTest() {
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .when().post(this.createProjectWebService)
                .then().statusCode(400);
    }

    @Test
    public void onlyEmptyNameRequestTest(){
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam(this.name,"")
                .when().post(this.createProjectWebService)
                .then().statusCode(400);
    }

    @Test
    public void onlyEmptyUIDRequestTest(){
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam(this.organizationUniqueIdentifier,"")
                .when().post(this.createProjectWebService)
                .then().statusCode(400);
    }

    @Test
    public void emptyNameRequestTest(){
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam(this.name,"")
                .formParam(this.organizationUniqueIdentifier,"monkey-labs")
                .when().post(this.createProjectWebService)
                .then().statusCode(400);
    }

    @Test
    public void emptyUIDRequestTest(){
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam(this.name,"xBase")
                .formParam(this.organizationUniqueIdentifier,"")
                .when().post(this.createProjectWebService)
                .then().statusCode(400);
    }

    @Test
    public void emptyParamsRequestTest(){
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam(this.organizationUniqueIdentifier,"")
                .formParam(this.name,"")
                .when().post(this.createProjectWebService)
                .then().statusCode(400);
    }

    @Test
    public void invalidOrganizationRequestTest(){
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam(this.organizationUniqueIdentifier,"congo-labs")
                .formParam(this.name,"xBase")
                .when().post(this.createProjectWebService)
                .then().statusCode(404);
    }

    @Test
    public void createProjectRequestTest(){
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam(this.organizationUniqueIdentifier,"monkey-labs")
                .formParam(this.name,"xBase")
                .when().post(this.createProjectWebService)
                .then().statusCode(200);
    }
}