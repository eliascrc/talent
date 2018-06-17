package cr.talent.ws.rest;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

/**
 * This class will automate tests for the forgot password web services.
 * @author Josue Cubero.
 */
public class PasswordResetRequestResourceTest extends FunctionalTest {

    private String token;

    @Test
    public void pingTest() {
        given().when().get("/").then().statusCode(200);
    }

    @Test
    public void emptyBodyRequestTest() {
        given()
                .contentType(ContentType.URLENC)
                .formParam("email","")
                .formParam("organizationIdentifier","")
                .when().post("/ws/passwordReset/forgotPassword")
                .then().statusCode(400);
    }

    @Test
    public void emptyEmailRequestTest() {
        given()
                .contentType(ContentType.URLENC)
                .formParam("email","")
                .formParam("organizationIdentifier","monkey-labs")
                .when().post("/ws/passwordReset/forgotPassword")
                .then().statusCode(400);
    }

    @Test
    public void emptyUIDRequestTest() {
        given()
                .contentType(ContentType.URLENC)
                .formParam("email","jo96cube@gmail.com")
                .formParam("organizationIdentifier","")
                .when().post("/ws/passwordReset/forgotPassword")
                .then().statusCode(400);
    }

    @Test
    public void nullBodyRequestTest() {
        given()
                .contentType(ContentType.URLENC)
                .when().post("/ws/passwordReset/forgotPassword")
                .then().statusCode(400);
    }


    @Test
    public void validRequestTest() {
        given()
                .contentType(ContentType.URLENC)
                .formParam("email","jo96cube@gmail.com")
                .formParam("organizationIdentifier","monkey-labs")
                .when().post("/ws/passwordReset/forgotPassword")
                .then().statusCode(200);
    }

    @Test
    public void getTokenRequestTest() {
        Response response = given()
                .contentType(ContentType.URLENC)
                .formParam("email","jo96cube@gmail.com")
                .formParam("organizationIdentifier","monkey-labs")
                .when().post("/ws/automation/forgotPasswordToken")
                .then().statusCode(200).extract().response();
        this.token = response.getBody().prettyPrint();
    }

    @Test
    public void invalidTokenRequestTest() {
        given()
                .queryParam("token","invalid-token")
                .when().get("/ws/passwordReset/new/")
                .then().statusCode(400);
    }

    @Test
    public void validTokenRequestTest() {

        this.getTokenRequestTest();

        given()
                .queryParam("token",this.token)
                .when().get("/ws/passwordReset/new/")
                .then().statusCode(200);
    }

    @Test
    public void invalidTokenForPasswordResetRequestTest() {
        given()
                .queryParam("token","invalid-token")
                .formParam("newPassword","TaLeNt.123")
                .when().post("/ws/passwordReset/reset/")
                .then().statusCode(400);
    }

    @Test
    public void passwordResetRequestTest() {

        this.getTokenRequestTest();

        given()
                .queryParam("token",this.token)
                .formParam("newPassword","TaLeNt.123")
                .when().post("/ws/passwordReset/reset/")
                .then().statusCode(200);
    }

}