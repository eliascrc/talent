package cr.talent.ws.rest;

import com.jayway.restassured.RestAssured;
import org.junit.BeforeClass;

/**
 * This class will serve as base for all tests which extend it.
 * @author Josue Cubero.
 */
public class FunctionalTest {

    @BeforeClass
    public static void setup() {
        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = Integer.valueOf(8080);
        } else {
            RestAssured.port = Integer.valueOf(port);
        }

        String basePath = System.getProperty("server.base");
        if(basePath==null){
            basePath = "/";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("server.host");
        if(baseHost==null){
            //comment baseHosts based on local or dev server tests.
            baseHost = "http://ws.talent.cr";
            //baseHost = "http://localhost/talent";
        }
        RestAssured.baseURI = baseHost;

    }

}