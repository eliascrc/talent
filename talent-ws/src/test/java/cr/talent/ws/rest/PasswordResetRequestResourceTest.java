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

    private final String email = "email";
    private final String organizationIdentifier = "organizationIdentifier";
    private final String forgotPasswordWebService = "/ws/passwordReset/forgotPassword";
    private final String checkForgotPasswordTokenWebService = "/ws/passwordReset/new/";
    private final String getForgotPasswordTokenWebService = "/ws/automation/forgotPasswordToken";
    private final String resetPasswordWebService = "/ws/passwordReset/reset/";
    private final String token = "token";
    private final String newPassword = "newPassword";
    private final String testEmail = "kendall.garcia@gmail.com";
    private String forgotPasswordToken;

    @Test
    public void pingTest() {
        given().when().get("/").then().statusCode(200);
    }

    @Test
    public void emptyBodyRequestTest() {
        given()
                .contentType(ContentType.URLENC)
                .formParam(this.email,"")
                .formParam(this.organizationIdentifier,"")
                .when().post(this.forgotPasswordWebService)
                .then().statusCode(400);
    }

    @Test
    public void emptyEmailRequestTest() {
        given()
                .contentType(ContentType.URLENC)
                .formParam(this.email,"")
                .formParam(this.organizationIdentifier,"monkey-labs")
                .when().post(this.forgotPasswordWebService)
                .then().statusCode(400);
    }

    @Test
    public void emptyUIDRequestTest() {
        given()
                .contentType(ContentType.URLENC)
                .formParam(this.email,this.testEmail)
                .formParam(this.organizationIdentifier,"")
                .when().post(this.forgotPasswordWebService)
                .then().statusCode(400);
    }

    @Test
    public void nullBodyRequestTest() {
        given()
                .contentType(ContentType.URLENC)
                .when().post(this.forgotPasswordWebService)
                .then().statusCode(400);
    }


    @Test
    public void validRequestTest() {
        given()
                .contentType(ContentType.URLENC)
                .formParam(this.email,this.testEmail)
                .formParam(this.organizationIdentifier,"monkey-labs")
                .when().post(this.forgotPasswordWebService)
                .then().statusCode(200);
    }

    @Test
    public void getTokenRequestTest() {
        Response response = given()
                .contentType(ContentType.URLENC)
                .formParam(this.email,this.testEmail)
                .formParam(this.organizationIdentifier,"monkey-labs")
                .when().post(this.getForgotPasswordTokenWebService)
                .then().statusCode(200).extract().response();

        this.forgotPasswordToken = response.getBody().prettyPrint();
    }

    @Test
    public void invalidTokenRequestTest() {
        given()
                .queryParam(this.token,"invalid-token")
                .when().get(this.checkForgotPasswordTokenWebService)
                .then().statusCode(400);
    }

    @Test
    public void validTokenRequestTest() {

        this.getTokenRequestTest();

        given()
                .queryParam(this.token,this.forgotPasswordToken)
                .when().get(this.checkForgotPasswordTokenWebService)
                .then().statusCode(200);
    }

    @Test
    public void invalidTokenForPasswordResetRequestTest() {
        given()
                .queryParam(this.token,"invalid-token")
                .formParam(this.newPassword,"TaLeNt.123")
                .when().post(this.resetPasswordWebService)
                .then().statusCode(400);
    }

    @Test
    public void passwordResetRequestTest() {

        this.getTokenRequestTest();

        given()
                .queryParam(this.token,this.forgotPasswordToken)
                .formParam(this.newPassword,"TaLeNt.123")
                .when().post(this.resetPasswordWebService)
                .then().statusCode(200);
    }

}