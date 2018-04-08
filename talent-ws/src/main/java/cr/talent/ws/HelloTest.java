package cr.talent.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/test")
public class HelloTest {

    @GET
    public Response test() {
        String testString = "Hello!";
        return Response.status(200).entity(testString).build();
    }
}
