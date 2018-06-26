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
import static org.hamcrest.Matchers.*;

/**
 * This class will automate tests for the login, loggedIn and authenticated web services.
 * As this is a flow, the tests will be executed in lexicographical order, using single alphabet letters as prefixes.
 * @author Josue Cubero.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginResourceTest extends FunctionalTest {

    private final String logInWebService = "/ws/login";
    private final String loggedInWebService = "/ws/user/loggedIn";
    private final String authenticatedWebService = "/ws/user/authenticated";
    private SessionFilter sessionFilter;

    public LoginResourceTest(){
        this.sessionFilter = new SessionFilter();
    }

    @Test
    public void aInValidLoggedInRequestTest(){

        given()
                .get(this.loggedInWebService)
                .then().statusCode(200)
                .body(containsString("false"));

    }

    @Test
    public void bInValidAuthenticatedUserRequestTest() {

        given()
            .get(this.authenticatedWebService)
            .then().statusCode(401);
    }

    @Test
    public void cLogInUserForTests(){

       given()
            .formParam("username","jo96cube@gmail.com")
            .formParam("password","TaLeNt.123")
            .formParam("organizationIdentifier","monkey-labs")
            .filter(this.sessionFilter)
            .contentType(ContentType.URLENC)
            .post(this.logInWebService)
            .then().statusCode(302);

    }

    @Test
    public void dValidLoggedInRequestTest(){

        this.cLogInUserForTests();

        given()
                .filter(this.sessionFilter)
                .get(this.loggedInWebService)
                .then().statusCode(200)
                .body(containsString("true"));

    }

    @Test
    public void eValidAuthenticatedUserRequestTest(){

        this.cLogInUserForTests();

        Response response = given()
                .filter(this.sessionFilter)
                .get(this.authenticatedWebService)
                .then().statusCode(200).extract().response();

        boolean accountNonExpired = true;
        boolean administrator = true;
        boolean credentialsNonExpired = true;
        String firstName = "Josué";
        String lastName = "Cubero";
        String organization = "monkey-labs";
        String username = "jo96cube@gmail.com";

        JsonPath jsonPath = response.jsonPath();

        assertEquals(jsonPath.get("accountNonExpired"),accountNonExpired);
        assertEquals(jsonPath.get("administrator"),administrator);
        assertEquals(jsonPath.get("credentialsNonExpired"),credentialsNonExpired);
        assertEquals(jsonPath.get("firstName"),firstName);
        assertEquals(jsonPath.get("lastName"),lastName);
        assertEquals(jsonPath.get("organization.uniqueIdentifier"),organization);
        assertEquals(jsonPath.get("username"),username);
    }
}
