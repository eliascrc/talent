package cr.talent.ws.rest;
import org.junit.Test;
import static com.jayway.restassured.RestAssured.*;

public class PrivacyPolicyResourceTest extends FunctionalTest {

    @Test
    public void pingTest() {
        given().when().get("/").then().statusCode(200);
    }

    @Test
    public void nullPlatformRequestTest(){
        given().when().get("/ws/content/privacyPolicy").then().statusCode(400);
    }

    @Test
    public void emptyPlatformRequestTest(){
        given().queryParam("platform","").when().get("/ws/content/privacyPolicy").then().statusCode(400);
    }

    @Test
    public void testWebPrivacyPolicy() {
        given().queryParam("platform","web").when().get("/ws/content/privacyPolicy").then().statusCode(200);
    }

    @Test
    public void testAndroidPrivacyPolicy() {
        given().queryParam("platform","android").when().get("/ws/content/privacyPolicy").then().statusCode(200);
    }

    //The following test was run on localhost, by setting the active ToS to inactive.
    /*@Test
    public void noActiveTermsOfServiceTest(){
        given().queryParam("platform","web").when().get("talent/ws/content/privacyPolicy").then().statusCode(204);
    }*/

}