package cr.talent.ws.rest;

import com.jayway.restassured.path.xml.XmlPath;
import org.junit.Test;
import static com.jayway.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

/**
 * This class will automate tests for the Terms of Service web service responses.
 * @author Josue Cubero.
 */
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

    /**
     * This test will extract the Web Content Properties from WebContentProperties in order to comparte the HTML tags content
     * received by the web service.
     */
    @Test
    public void testWebTermsOfService() {
        XmlPath xmlPath =
                given().queryParam("platform","web").when()
                        .get("/ws/content/termsOfService").then().statusCode(200).extract().response().htmlPath();

        assertEquals(xmlPath.get("html.head.style"),
                WebContentProperties.WEB_TERMS_OF_SERVICE_HEAD_STYLE);
        assertEquals(xmlPath.get("html.head.title"),
                WebContentProperties.TERMS_OF_SERVICE_HEAD_TITLE);
        assertEquals(xmlPath.get("html.body.p"),
                WebContentProperties.WEB_TERMS_OF_SERVICE_BODY_TEXT);

    }

    /**
     * This test will extract the Web Content Properties from WebContentProperties in order to comparte the HTML tags content
     * received by the web service.
     */
    @Test
    public void testAndroidTermsOfService() {
        XmlPath xmlPath =
                given().queryParam("platform","android").when()
                        .get("/ws/content/termsOfService").then().statusCode(200).extract().response().htmlPath();

        assertEquals(xmlPath.get("html.head.title"),
                WebContentProperties.TERMS_OF_SERVICE_HEAD_TITLE);
        assertEquals(xmlPath.get("html.body.div.h3[0]"),
                WebContentProperties.MOBILE_TERMS_OF_SERVICE_BODY_DIV_H3_0);
        assertEquals(xmlPath.get("html.body.div.h3[1]"),
                WebContentProperties.MOBILE_TERMS_OF_SERVICE_BODY_DIV_H3_1);
        assertEquals(xmlPath.get("html.body.div.p[0]"),
                WebContentProperties.MOBILE_TERMS_OF_SERVICE_BODY_DIV_P_0);
        assertEquals(xmlPath.get("html.body.div.p[1]"),
                WebContentProperties.MOBILE_TERMS_OF_SERVICE_BODY_DIV_P_1);
        assertEquals(xmlPath.get("html.body.div.p[2]"),
                WebContentProperties.MOBILE_TERMS_OF_SERVICE_BODY_DIV_P_2);
        assertEquals(xmlPath.get("html.body.div.p[3]"),
                WebContentProperties.MOBILE_TERMS_OF_SERVICE_BODY_DIV_P_3);
    }

    //The following test was run on localhost, by setting the active PP to inactive.
    /*@Test
    public void noActiveTermsOfServiceTest(){
        given().queryParam("platform","web").when()when().get("talent/ws/content/termsOfService").then().statusCode(204);
    }*/

}