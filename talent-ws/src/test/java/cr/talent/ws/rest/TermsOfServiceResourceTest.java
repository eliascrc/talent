package cr.talent.ws.rest;

import org.junit.Test;
import static com.jayway.restassured.RestAssured.*;

public class TermsOfServiceResourceTest extends FunctionalTest {

    @Test
    public void pingTest() {
        given().when().get("/").then().statusCode(200);
    }

    @Test
    public void pingTermsOfService() {
        given().when().get("/ws/content/termsOfService").then().statusCode(200);
    }

    //The following test was run on localhost, by setting the active PP to inactive.
    /*@Test
    public void noActiveTermsOfServiceTest(){
        given().when().get("talent/ws/content/termsOfService").then().statusCode(204);
    }*/

}