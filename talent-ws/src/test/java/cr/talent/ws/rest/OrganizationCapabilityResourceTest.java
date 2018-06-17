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
                .when().post("/ws/organization/capability/create")
                .then().statusCode(401);
    }

    @Test
    public void noHeaderRequestTest() {
        given()
                .filter(this.sessionFilter)
                .when().post("/ws/organization/capability/create")
                .then().statusCode(400);
    }

    @Test
    public void nullBodyRequestTest() {
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .when().post("/ws/organization/capability/create")
                .then().statusCode(400);
    }

    @Test
    public void onlyEmptyNameRequestTest(){
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam("name","")
                .when().post("/ws/organization/capability/create")
                .then().statusCode(400);
    }

    @Test
    public void onlyEmptyUIDRequestTest(){
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam("organizationUniqueIdentifier","")
                .when().post("/ws/organization/capability/create")
                .then().statusCode(400);
    }

    @Test
    public void emptyNameRequestTest(){
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam("name","")
                .formParam("organizationUniqueIdentifier","monkey-labs")
                .when().post("/ws/organization/capability/create")
                .then().statusCode(400);
    }

    @Test
    public void emptyUIDRequestTest(){
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam("name","Apache Hive Data Analyst")
                .formParam("organizationUniqueIdentifier","")
                .when().post("/ws/organization/capability/create")
                .then().statusCode(400);
    }

    @Test
    public void emptyParamsRequestTest(){
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam("organizationUniqueIdentifier","")
                .formParam("name","")
                .when().post("/ws/organization/capability/create")
                .then().statusCode(400);
    }

    @Test
    public void invalidOrganizationRequestTest(){
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam("organizationUniqueIdentifier","congo-labs")
                .formParam("name","Apache Hive Data Analyst")
                .when().post("/ws/organization/capability/create")
                .then().statusCode(404);
    }

    @Test
    public void existingCapabilityRequestTest(){
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam("organizationUniqueIdentifier","monkey-labs")
                .formParam("name","Mobile Developer")
                .when().post("/ws/organization/capability/create")
                .then().statusCode(409);
    }

    /*@Test
    public void createCapabilityRequestTest(){
        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam("organizationUniqueIdentifier","monkey-labs")
                .formParam("name","QA Analyst")
                .when().post("/ws/organization/capability/create")
                .then().statusCode(200);
    }*/
}