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
 * As this is a flow, the tests will be executed in lexicographical order, using single alphabet letters as prefixes.
 * @author Josue Cubero.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpResourceTest extends FunctionalTest {

    private final String stepOneWebService = "/ws/signUp/stepOne";
    private final String stepTwoWebService = "/ws/signUp/stepTwo";
    private final String getSignUpCodeWebService = "/ws/automation/signUpCode";
    private final String stepThreeWebService = "/ws/organization/create";
    private final String stepFourInvitationsWebService = "/ws/invitation/send";
    private final String stepFourGetInviteLinkWebService = "/ws/invitation/inviteLink";
    private final String stepFourCreateInviteLinkWebService = "/ws/invitation/inviteLink/create";
    private final String deleteTechnicalResourceWebService = "/ws/automation/deleteTechnicalResource";
    private final String deleteOrganizationWebService = "/ws/automation/deleteOrganization";

    private final String firstName = "firstName";
    private final String lastName = "lastName";
    private final String nickname = "nickname";
    private final String email = "email";
    private final String password = "password";
    private final String code = "code";
    private final String uniqueIdentifier = "uniqueIdentifier";
    private final String name = "name";
    private final String username = "username";
    private final String termsOfServiceAccepted = "termsOfServiceAccepted";
    private final String organizationIdentifier = "organizationIdentifier";
    private final String invitations = "invitations";

    private String forgotPasswordCode;
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
                .when().post(this.stepOneWebService)
                .then().statusCode(400);
    }

    @Test
    public void cEmptyBodyRequestTestForStepOne() {
        given()
                .formParam(this.firstName,"")
                .formParam(this.lastName,"")
                .formParam(this.email,"")
                .formParam(this.password,"")
                .when().post(this.stepOneWebService)
                .then().statusCode(400);
    }

    @Test
    public void dEmptyFirstNameRequestTestForStepOne() {
        given()
                .formParam(this.firstName,"")
                .formParam(this.lastName,"Kiske")
                .formParam(this.email,"xbaseucr@gmail.com")
                .formParam(this.password,"Talent.123")
                .when().post(this.stepOneWebService)
                .then().statusCode(400);
    }

    @Test
    public void eEmptyLastNameRequestTestForStepOne() {
        given()
                .formParam(this.firstName,"Michael")
                .formParam(this.lastName,"")
                .formParam(this.email,"xbaseucr@gmail.com")
                .formParam(this.password,"")
                .when().post(this.stepOneWebService)
                .then().statusCode(400);
    }

    @Test
    public void fEmptyEmailRequestTestForStepOne() {
        given()
                .formParam(this.firstName,"Michael")
                .formParam(this.lastName,"Kiske")
                .formParam(this.email,"")
                .formParam(this.password,"Talent.123")
                .when().post(this.stepOneWebService)
                .then().statusCode(400);
    }

    @Test
    public void gEmptyPasswordRequestTestForStepOne() {
        given()
                .formParam(this.firstName,"Michael")
                .formParam(this.lastName,"Kiske")
                .formParam(this.email,"xbaseucr@gmail.com")
                .formParam(this.password,"")
                .when().post(this.stepOneWebService)
                .then().statusCode(400);
    }

    @Test
    public void hInvalidPasswordRequestTestForStepOne() {
        given()
                .formParam(this.firstName,"Michael")
                .formParam(this.lastName,"Kiske")
                .formParam(this.email,"xbaseucr@gmail.com")
                .formParam(this.password,"invalidpassword")
                .when().post(this.stepOneWebService)
                .then().statusCode(400);
    }

    @Test
    public void iValidRequestTestForStepOne() {

        Response response = given()
                .formParam(this.firstName,"Michael")
                .formParam(this.lastName,"Kiske")
                .formParam(this.nickname,"Michi")
                .formParam(this.email,"xbaseucr@gmail.com")
                .formParam(this.password,"Talent.123")
                .when().post(this.stepOneWebService)
                .then().statusCode(200).extract().response();

        boolean accountNonExpired = true;
        boolean administrator = true;
        boolean credentialsNonExpired = true;
        String firstName = "Michael";
        String lastName = "Kiske";
        String nickname = "Michi";
        String organization = null;
        String username = "xbaseucr@gmail.com";

        JsonPath jsonPath = response.jsonPath();

        assertEquals(jsonPath.get("accountNonExpired"),accountNonExpired);
        assertEquals(jsonPath.get("administrator"),administrator);
        assertEquals(jsonPath.get("credentialsNonExpired"),credentialsNonExpired);
        assertEquals(jsonPath.get("firstName"),firstName);
        assertEquals(jsonPath.get("lastName"),lastName);
        assertEquals(jsonPath.get("nickname"),nickname);
        assertEquals(jsonPath.get("organization"),organization);
        assertEquals(jsonPath.get("username"),username);
    }

    @Test
    public void jGetCodeRequestTestForStepTwo() {

        Response response = given()
                .contentType(ContentType.URLENC)
                .formParam(this.email,"xbaseucr@gmail.com")
                .when().post(this.getSignUpCodeWebService)
                .then().statusCode(200).extract().response();

        this.forgotPasswordCode = response.getBody().prettyPrint();

    }

    @Test
    public void kNullBodyRequestTestForStepTwo() {
        given()
                .when().post(this.stepTwoWebService)
                .then().statusCode(400);
    }

    @Test
    public void lEmptyBodyRequestTestForStepTwo() {
        given()
                .formParam(this.email,"")
                .formParam(this.code,"")
                .when().post(this.stepTwoWebService)
                .then().statusCode(400);
    }

    @Test
    public void mEmptyEmailRequestTestForStepTwo() {

        this.jGetCodeRequestTestForStepTwo();

        given()
                .formParam(this.email,"")
                .formParam(this.code,this.forgotPasswordCode)
                .when().post(this.stepTwoWebService)
                .then().statusCode(400);
    }

    @Test
    public void nEmptyCodeRequestTestForStepTwo() {
        given()
                .formParam(this.email,"xbaseucr@gmail.com")
                .formParam(this.code,"")
                .when().post(this.stepTwoWebService)
                .then().statusCode(400);
    }

    @Test
    public void oInvalidRequestTestForStepTwo() {

        this.jGetCodeRequestTestForStepTwo();

        given()
                .formParam(this.email,"invalidemail@gmail.com")
                .formParam(this.code,this.forgotPasswordCode)
                .when().post(this.stepTwoWebService)
                .then().statusCode(409);
    }

    @Test
    public void pValidRequestTestForStepTwo() {

        this.jGetCodeRequestTestForStepTwo();

        given()
                .formParam(this.email,"xbaseucr@gmail.com")
                .formParam(this.code,this.forgotPasswordCode)
                .when().post(this.stepTwoWebService)
                .then().statusCode(200);
    }

    @Test
    public void qCreateValidOrganizationRequestTestForStepThree(){
        given()
                .contentType(ContentType.URLENC)
                .formParam(this.uniqueIdentifier,"helloween-org")
                .formParam(this.name,"Helloween Org")
                .formParam(this.username, "xbaseucr@gmail.com")
                .formParam(this.termsOfServiceAccepted,true)
                .when().post(this.stepThreeWebService)
                .then().statusCode(200);
    }

    @Test
    public void rNoAuthRequestForStepFourInvitations(){

        given()
                .contentType(ContentType.URLENC)
                .post(this.stepFourInvitationsWebService)
                .then().statusCode(401);

    }

    @Test
    public void sNoAuthRequestForStepFourCreateInviteLink(){

        given()
                .contentType(ContentType.URLENC)
                .post(this.stepFourCreateInviteLinkWebService)
                .then().statusCode(401);

    }

    @Test
    public void tNoAuthRequestForStepFourGetInviteLink(){

        given()
                .contentType(ContentType.URLENC)
                .post(this.stepFourGetInviteLinkWebService)
                .then().statusCode(401);

    }

    @Test
    public void uLogInUserForTests(){

        given()
                .formParam(this.username,"xbaseucr@gmail.com")
                .formParam(this.password,"Talent.123")
                .formParam(this.organizationIdentifier,"helloween-org")
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
                .post(this.stepFourInvitationsWebService)
                .then().statusCode(400);

    }

    @Test
    public void wEmptyBodyRequestForStepFourInvitations(){

        this.uLogInUserForTests();

        given()
                .filter(this.sessionFilter)
                .formParam(this.invitations,"")
                .contentType(ContentType.URLENC)
                .post(this.stepFourInvitationsWebService)
                .then().statusCode(400);

    }

    @Test
    public void xValidRequestForStepFourInvitations(){

        this.uLogInUserForTests();

        String invitations = "{\"invitations\":[{\"email\":\"jo96guerre@gmail.com\",\"firstName\":\"Joaquin\",\"lastName\":\"Guerrero\",\"token\":\"token\",\"isValid\":true}," +
                "{\"email\":\"jo96cube@hotmail.com\",\"firstName\":\"Josue\",\"lastName\":\"Cubero\",\"token\":\"toke\",\"isValid\":false}" +
                "]}";

        given()
                .filter(this.sessionFilter)
                .formParam(this.invitations,invitations)
                .contentType(ContentType.URLENC)
                .post(this.stepFourInvitationsWebService)
                .then().statusCode(200);

    }

    /*
    @Test
    public void yLimitReachedForStepFourInvitations(){

        this.uLogInUserForTests();

        given()
                .filter(this.sessionFilter)
                .formParam(this.emails,"example0@gmail.com")
                .formParam(this.emails,"example1@gmail.com")
                .formParam(this.emails,"example2@gmail.com")
                .formParam(this.emails,"example3@gmail.com")
                .formParam(this.emails,"example4@gmail.com")
                .formParam(this.emails,"example5@gmail.com")
                .formParam(this.emails,"example6@gmail.com")
                .formParam(this.emails,"example7@gmail.com")
                .formParam(this.emails,"example8@gmail.com")
                .formParam(this.emails,"example9@gmail.com")
                .formParam(this.emails,"example10@gmail.com")
                .contentType(ContentType.URLENC)
                .post(this.stepFourInvitationsWebService)
                .then().statusCode(409);

    }*/

    @Test
    public void zNoLinkCreatedForStepFourGetInviteLink(){

        this.uLogInUserForTests();

        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .get(this.stepFourGetInviteLinkWebService)
                .then().statusCode(204);

    }

    @Test
    public void zaValidRequestForStepFourCreateInviteLink(){

        this.uLogInUserForTests();

        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .post(this.stepFourCreateInviteLinkWebService)
                .then().statusCode(200);

    }

    @Test
    public void zbMethodNotAllowedRequestForStepFourGetInviteLink(){

        this.uLogInUserForTests();

        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .post(this.stepFourGetInviteLinkWebService)
                .then().statusCode(405);

    }

    @Test
    public void zcValidRequestForStepFourGetInviteLink(){

        this.uLogInUserForTests();

        given()
                .filter(this.sessionFilter)
                .contentType(ContentType.URLENC)
                .get(this.stepFourGetInviteLinkWebService)
                .then().statusCode(200);

    }


    @Test
    public void zdDeleteTechnicalResourceRequestTest() {

        given()
                .formParam(this.email,"xbaseucr@gmail.com")
                .when().post(this.deleteTechnicalResourceWebService)
                .then().statusCode(200);
    }

    @Test
    public void zeDeleteOrganizationRequestTest() {

        given()
                .formParam(this.uniqueIdentifier,"helloween-org")
                .when().post(this.deleteOrganizationWebService)
                .then().statusCode(200);
    }

}