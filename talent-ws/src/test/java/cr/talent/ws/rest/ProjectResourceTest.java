package cr.talent.ws.rest;

import com.jayway.restassured.filter.session.SessionFilter;
import com.jayway.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static com.jayway.restassured.RestAssured.given;

/**
 * This class will automate tests for the create project web service.
 * @author Josue Cubero.
 */
public class ProjectResourceTest extends FunctionalTest {

    private final String createProjectWebService = "/ws/organization/project/create";
    private final String name = "name";
    private final String startDate = "startDate";
    private final String projectLead = "projectLead";
    private final String description = "description";
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
    public void onlyNullStartDateTest(){
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam(this.startDate,(Object)null)
                .when().post(this.createProjectWebService)
                .then().statusCode(400);
    }

    @Test
    public void onlyEmptyProjectLeadTest(){
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam(this.projectLead,"")
                .when().post(this.createProjectWebService)
                .then().statusCode(400);
    }

    @Test
    public void onlyEmptyDescriptionTest(){
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam(this.description,"")
                .when().post(this.createProjectWebService)
                .then().statusCode(400);
    }

    @Test
    public void nullStartDateRequestTest(){
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam(this.name,"name")
                .formParam(this.startDate,(Object)null)
                .formParam(this.description,"description")
                .when().post(this.createProjectWebService)
                .then().statusCode(400);
    }



    @Test
    public void emptyNullParamsRequestTest(){
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam(this.name,"")
                .formParam(this.startDate,(Object)null)
                .formParam(this.projectLead,"")
                .formParam(this.description,"")
                .when().post(this.createProjectWebService)
                .then().statusCode(400);
    }

    /*
    @Test
    public void emptyProjectLeadRequestTest(){
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam(this.name,"name")
                .formParam(this.startDate,new Date())
                .formParam(this.projectLead,"")
                .formParam(this.description,"description")
                .when().post(this.createProjectWebService)
                .then().statusCode(200);//projectLead is optional
    }*/


}