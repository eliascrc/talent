package cr.talent.ws.rest;

import com.jayway.restassured.filter.session.SessionFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

/**
 * This class will automate tests for the sign up web services.
 * As this is a flow, the test will be executed in lexicographical order, using single alphabet letters as prefixes.
 * @author Josue Cubero.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpResourceTest extends FunctionalTest {

    private String code;
    private SessionFilter sessionFilter;

    public SignUpResourceTest(){
        this.sessionFilter = new SessionFilter();
    }

    @Test
    public void aPingTest() {
        given().when().get("/").then().statusCode(200);
    }

    @Test
    public void bNullBodyRequestTestForStepOne() {
        given()
                .when().post("/ws/signUp/stepOne")
                .then().statusCode(400);
    }

    @Test
    public void cEmptyBodyRequestTestForStepOne() {
        given()
                .formParam("firstName","")
                .formParam("lastName","")
                .formParam("email","")
                .formParam("password","")
                .when().post("/ws/signUp/stepOne")
                .then().statusCode(400);
    }

    @Test
    public void dEmptyFirstNameRequestTestForStepOne() {
        given()
                .formParam("firstName","")
                .formParam("lastName","Kiske")
                .formParam("email","xbaseucr@gmail.com")
                .formParam("password","Talent.123")
                .when().post("/ws/signUp/stepOne")
                .then().statusCode(400);
    }

    @Test
    public void eEmptyLastNameRequestTestForStepOne() {
        given()
                .formParam("firstName","Michael")
                .formParam("lastName","")
                .formParam("email","xbaseucr@gmail.com")
                .formParam("password","")
                .when().post("/ws/signUp/stepOne")
                .then().statusCode(400);
    }

    @Test
    public void fEmptyEmailRequestTestForStepOne() {
        given()
                .formParam("firstName","Michael")
                .formParam("lastName","Kiske")
                .formParam("email","")
                .formParam("password","Talent.123")
                .when().post("/ws/signUp/stepOne")
                .then().statusCode(400);
    }

    @Test
    public void gEmptyPasswordRequestTestForStepOne() {
        given()
                .formParam("firstName","Michael")
                .formParam("lastName","Kiske")
                .formParam("email","xbaseucr@gmail.com")
                .formParam("password","")
                .when().post("/ws/signUp/stepOne")
                .then().statusCode(400);
    }

    @Test
    public void hInvalidPasswordRequestTestForStepOne() {
        given()
                .formParam("firstName","Michael")
                .formParam("lastName","Kiske")
                .formParam("email","xbaseucr@gmail.com")
                .formParam("password","invalidpassword")
                .when().post("/ws/signUp/stepOne")
                .then().statusCode(400);
    }

    @Test
    public void iValidRequestTestForStepOne() {

        Response response = given()
                .formParam("firstName","Michael")
                .formParam("lastName","Kiske")
                .formParam("email","xbaseucr@gmail.com")
                .formParam("password","Talent.123")
                .when().post("/ws/signUp/stepOne")
                .then().statusCode(200).extract().response();

        boolean accountNonExpired = true;
        boolean administrator = true;
        boolean credentialsNonExpired = true;
        String firstName = "Michael";
        String lastName = "Kiske";
        String organization = null;
        String username = "xbaseucr@gmail.com";

        JsonPath jsonPath = response.jsonPath();

        assertEquals(jsonPath.get("accountNonExpired"),accountNonExpired);
        assertEquals(jsonPath.get("administrator"),administrator);
        assertEquals(jsonPath.get("credentialsNonExpired"),credentialsNonExpired);
        assertEquals(jsonPath.get("firstName"),firstName);
        assertEquals(jsonPath.get("lastName"),lastName);
        assertEquals(jsonPath.get("organization"),organization);
        assertEquals(jsonPath.get("username"),username);
    }

    @Test
    public void jGetCodeRequestTestForStepTwo() {

        Response response = given()
                .contentType(ContentType.URLENC)
                .formParam("email","xbaseucr@gmail.com")
                .when().post("/ws/automation/signUpCode")
                .then().statusCode(200).extract().response();

        this.code = response.getBody().prettyPrint();

    }

    @Test
    public void kNullBodyRequestTestForStepTwo() {
        given()
                .when().post("/ws/signUp/stepTwo")
                .then().statusCode(400);
    }

    @Test
    public void lEmptyBodyRequestTestForStepTwo() {
        given()
                .formParam("email","")
                .formParam("code","")
                .when().post("/ws/signUp/stepTwo")
                .then().statusCode(400);
    }

    @Test
    public void mEmptyEmailRequestTestForStepTwo() {

        this.jGetCodeRequestTestForStepTwo();

        given()
                .formParam("email","")
                .formParam("code",this.code)
                .when().post("/ws/signUp/stepTwo")
                .then().statusCode(400);
    }

    @Test
    public void nEmptyCodeRequestTestForStepTwo() {
        given()
                .formParam("email","xbaseucr@gmail.com")
                .formParam("code","")
                .when().post("/ws/signUp/stepTwo")
                .then().statusCode(400);
    }

    @Test
    public void oInvalidRequestTestForStepTwo() {

        this.jGetCodeRequestTestForStepTwo();

        given()
                .formParam("email","invalidemail@gmail.com")
                .formParam("code",this.code)
                .when().post("/ws/signUp/stepTwo")
                .then().statusCode(409);
    }

    @Test
    public void pValidRequestTestForStepTwo() {

        this.jGetCodeRequestTestForStepTwo();

        given()
                .formParam("email","xbaseucr@gmail.com")
                .formParam("code",this.code)
                .when().post("/ws/signUp/stepTwo")
                .then().statusCode(200);
    }

    @Test
    public void qCreateValidOrganizationRequestTestForStepThree(){
        given()
                .contentType(ContentType.URLENC)
                .formParam("uniqueIdentifier","helloween-org")
                .formParam("name","Helloween Org")
                .formParam("username", "xbaseucr@gmail.com")
                .formParam("termsOfServiceAccepted",true)
                .when().post("/ws/organization/create")
                .then().statusCode(200);
    }

    @Test
    public void rNoAuthRequestForStepFourInvitations(){

        given()
                .contentType(ContentType.URLENC)
                .post("/ws/invitation/send")
                .then().statusCode(401);

    }

    @Test
    public void sNoAuthRequestForStepFourCreateInviteLink(){

        given()
                .contentType(ContentType.URLENC)
                .post("/ws/invitation/inviteLink/create")
                .then().statusCode(401);

    }

    @Test
    public void tNoAuthRequestForStepFourGetInviteLink(){

        given()
                .contentType(ContentType.URLENC)
                .post("/ws/invitation/inviteLink")
                .then().statusCode(401);

    }

    @Test
    public void uLogInUserForTests(){

        given()
                .formParam("username","xbaseucr@gmail.com")
                .formParam("password","Talent.123")
                .formParam("organizationIdentifier","helloween-org")
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .post("/ws/login")
                .then().statusCode(302);

    }

    @Test
    public void vNullBodyRequestForStepFourInvitations(){

        this.uLogInUserForTests();

        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .post("/ws/invitation/send")
                .then().statusCode(400);

    }

    @Test
    public void wEmptyBodyRequestForStepFourInvitations(){

        this.uLogInUserForTests();

        given()
                .filter(this.sessionFilter)
                .formParam("emails","")
                .contentType(ContentType.URLENC)
                .post("/ws/invitation/send")
                .then().statusCode(400);

    }

    @Test
    public void xValidRequestForStepFourInvitations(){

        this.uLogInUserForTests();

        given()
                .filter(this.sessionFilter)
                .formParam("emails","jo96guerre@gmail.com")
                .contentType(ContentType.URLENC)
                .post("/ws/invitation/send")
                .then().statusCode(200);

    }

    @Test
    public void yLimitReachedForStepFourInvitations(){

        this.uLogInUserForTests();

        given()
                .filter(this.sessionFilter)
                .formParam("emails","example0@gmail.com")
                .formParam("emails","example1@gmail.com")
                .formParam("emails","example2@gmail.com")
                .formParam("emails","example3@gmail.com")
                .formParam("emails","example4@gmail.com")
                .formParam("emails","example5@gmail.com")
                .formParam("emails","example6@gmail.com")
                .formParam("emails","example7@gmail.com")
                .formParam("emails","example8@gmail.com")
                .formParam("emails","example9@gmail.com")
                .formParam("emails","example10@gmail.com")
                .contentType(ContentType.URLENC)
                .post("/ws/invitation/send")
                .then().statusCode(409);

    }

    @Test
    public void zNoLinkCreatedForStepFourGetInviteLink(){

        this.uLogInUserForTests();

        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .get("/ws/invitation/inviteLink")
                .then().statusCode(204);

    }

    @Test
    public void zaValidRequestForStepFourCreateInviteLink(){

        this.uLogInUserForTests();

        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .post("/ws/invitation/inviteLink/create")
                .then().statusCode(200);

    }

    @Test
    public void zbMethodNotAllowedRequestForStepFourGetInviteLink(){

        this.uLogInUserForTests();

        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .post("/ws/invitation/inviteLink")
                .then().statusCode(405);

    }

    @Test
    public void zcValidRequestForStepFourGetInviteLink(){

        this.uLogInUserForTests();

        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .get("/ws/invitation/inviteLink")
                .then().statusCode(200);

    }


    @Test
    public void zdDeleteTechnicalResourceRequestTest() {

        given()
                .formParam("email","xbaseucr@gmail.com")
                .when().post("/ws/automation/deleteTechnicalResource")
                .then().statusCode(200);
    }

    @Test
    public void zeDeleteOrganizationRequestTest() {

        given()
                .formParam("uniqueIdentifier","helloween-org")
                .when().post("/ws/automation/deleteOrganization")
                .then().statusCode(200);
    }

}