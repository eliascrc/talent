package cr.talent.ws.rest;

import com.sun.jersey.multipart.FormDataParam;
import cr.talent.core.image.organizationLogo.service.OrganizationLogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import javax.ws.rs.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;

/**
 * Web services to manage organizations logo in Amazon S3.
 *
 * @author María José Cubero
 */

@Component
@Scope("request")
@Path("/organizationLogo")
public class OrganizationLogoResource {

    @Autowired
    OrganizationLogoService organizationLogoService;

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadImage(@FormDataParam("file") InputStream file) {
        System.out.println(file);
        if (StringUtils.isEmpty(file))
            return Response.status(Response.Status.BAD_REQUEST).build();

        this.organizationLogoService.uploadOrganizationLogo(file);
        return Response.status(200).build();
    }

    @POST
    @Path("/delete")
    public Response deleteImage(){
        try{
            this.organizationLogoService.deleteOrganizationLogo();
            return Response.status(200).build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
