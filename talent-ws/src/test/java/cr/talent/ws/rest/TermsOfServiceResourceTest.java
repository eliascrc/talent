package cr.talent.ws.rest;

import org.junit.Test;
import static com.jayway.restassured.RestAssured.*;

public class TermsOfServiceResourceTest extends FunctionalTest {

    @Test
    public void pingTest() {
        given().when().get("/").then().statusCode(200);
    }

    @Test
    public void nullPlatformRequestTest(){
        given().when().get("/ws/content/termsOfService").then().statusCode(400);
    }

    @Test
    public void emptyPlatformRequestTest(){
        given().queryParam("platform","").when().get("/ws/content/termsOfService").then().statusCode(400);
    }

    @Test
    public void testWebTermsOfService() {
        given().queryParam("platform","web").when().get("/ws/content/termsOfService").then().statusCode(200);
    }

    @Test
    public void testAndroidTermsOfService() {
        given().queryParam("platform","android").when().get("/ws/content/termsOfService").then().statusCode(200);
    }


    //The following test was run on localhost, by setting the active PP to inactive.
    /*@Test
    public void noActiveTermsOfServiceTest(){
        given().queryParam("platform","web").when()when().get("talent/ws/content/termsOfService").then().statusCode(204);
    }*/

}