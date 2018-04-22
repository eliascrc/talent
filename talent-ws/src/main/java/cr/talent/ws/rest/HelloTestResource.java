package cr.talent.ws.rest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Component
@Scope("request")
@Path("/admin/test")
public class HelloTestResource {

    @GET
    public Response test() {
        String testString = "Hello!";
        return Response.status(200).entity(testString).build();
    }
}
