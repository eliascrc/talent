package cr.talent.ws.rest;

import com.jayway.restassured.filter.session.SessionFilter;
import com.jayway.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

/**
 * This class will automate tests for the predefined capability web service.
 * @author Josue Cubero.
 */
public class PredefinedCapabilityResourceTest extends FunctionalTest {

    private SessionFilter sessionFilter;

    public PredefinedCapabilityResourceTest(){
        this.sessionFilter = new SessionFilter();
    }

    /**
     * The following tests were run on localhost by changing Spring Security permission to permitAll() on ws/admin/* web services
     * Also, in order to run them, uncomment them, change base host on functional test class.
     * Filter is commented because it is not needed yet but it will when admin module is implemented.
     * The logInUserForTests, pingTest and noAuthRequestTest should remain commented, as they will serve when the admin module
     * actually exist.
     */


    /*
    @Before
    public void logInUserForTests(){

        given()
                .formParam("username","jo96cube@gmail.com")
                .formParam("password","Talent.123")
                .formParam("organizationIdentifier","monkey-labs")
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .post("/talent/ws/login")
                .then().statusCode(302);

    }

    @Test
    public void pingTest() {
        given().when().get("/").then().statusCode(200);
    }

    @Test
    public void noAuthRequestTest() {
        given()
                .when().post("/ws/admin/capability/create")
                .then().statusCode(401);
    }*/

    /*@Test
    public void noHeaderRequestTest() {
        given()
                //.filter(this.sessionFilter)
                .when().post("/talent/ws/admin/capability/create")
                .then().statusCode(400);
    }

    @Test
    public void nullBodyRequestTest() {
        given()
                //.filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .when().post("/talent/ws/admin/capability/create")
                .then().statusCode(400);
    }

    @Test
    public void emptyNameRequestTest(){
        given()
                //.filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam("name","")
                .when().post("/talent/ws/admin/capability/create")
                .then().statusCode(400);
    }

    @Test
    public void existingPredefinedCapabilityRequestTest(){
        given()
                //.filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam("name","Mobile Developer")
                .when().post("/talent/ws/admin/capability/create")
                .then().statusCode(409);
    }*/

    /*@Test
    public void createPredefinedCapabilityRequestTest(){
        given()
                //.filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .formParam("name",".NET Developer")
                .when().post("/talent/ws/admin/capability/create")
                .then().statusCode(200);
    }*/
}