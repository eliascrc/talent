package cr.talent.ws.rest;

import com.jayway.restassured.path.xml.XmlPath;
import org.junit.Test;
import static com.jayway.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

/**
 * This class will automate tests for the Privacy Policy web service responses.
 * @author Josue Cubero.
 */
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
        XmlPath xmlPath =
                given().queryParam("platform","web").when()
                        .get("/ws/content/privacyPolicy").then().statusCode(200).extract().response().htmlPath();

        assertEquals(xmlPath.get("html.head.style"),
                WebContentProperties.WEB_PRIVACY_POLICY_HEAD_STYLE);
        assertEquals(xmlPath.get("html.head.title"),
                WebContentProperties.PRIVACY_POLICY_HEAD_TITLE);
        assertEquals(xmlPath.get("html.body.p"),
                WebContentProperties.WEB_PRIVACY_POLICY_BODY_TEXT);

    }

    @Test
    public void testAndroidPrivacyPolicy() {
        XmlPath xmlPath =
                given().queryParam("platform","android").when()
                        .get("/ws/content/privacyPolicy").then().statusCode(200).extract().response().htmlPath();

        assertEquals(xmlPath.get("html.head.title"),
                WebContentProperties.PRIVACY_POLICY_HEAD_TITLE);
        assertEquals(xmlPath.get("html.body.div.h3[0]"),
                WebContentProperties.MOBILE_PRIVACY_POLICY_BODY_DIV_H3_0);
        assertEquals(xmlPath.get("html.body.div.h3[1]"),
                WebContentProperties.MOBILE_PRIVACY_POLICY_BODY_DIV_H3_1);
        assertEquals(xmlPath.get("html.body.div.p[0]"),
                WebContentProperties.MOBILE_PRIVACY_POLICY_BODY_DIV_P_0);
        assertEquals(xmlPath.get("html.body.div.p[1]"),
                WebContentProperties.MOBILE_PRIVACY_POLICY_BODY_DIV_P_1);
    }

    //The following test was run on localhost, by setting the active ToS to inactive.
    /*@Test
    public void noActiveTermsOfServiceTest(){
        given().queryParam("platform","web").when().get("talent/ws/content/privacyPolicy").then().statusCode(204);
    }*/

}