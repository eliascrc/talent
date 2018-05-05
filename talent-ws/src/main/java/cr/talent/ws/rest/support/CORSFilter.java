package cr.talent.ws.rest.support;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

public class CORSFilter implements ContainerResponseFilter {
    @Override
    public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {

        response.getHttpHeaders().add("Access-Control-Allow-Origin", "*");
        response.getHttpHeaders().add("Access-Control-Allow-Credentials", "true");
        response.getHttpHeaders().add("Access-Control-Allow-Headers", "content-type, origin, authorization");
        response.getHttpHeaders().add("Access-Control-Allow-Methods","GET, POST, DELETE, PUT, OPTIONS, ACCEPT");

        return response;
    }
}
