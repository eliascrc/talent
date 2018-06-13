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

    private static long MAX_FILE_SIZE = 5242880;

    @Autowired
    OrganizationLogoService organizationLogoService;

    /**
     * uploads the organization picture.
     *
     * @param file
     * @param contentLength
     * @return 200 if the image was uploaded.
     * @return 400 if the image is empty or bigger than 5 MB.
     */
    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadImage(@FormDataParam("file") InputStream file,
                                @HeaderParam("content-length") long contentLength) {
        System.out.println(file);
        if (StringUtils.isEmpty(file) || contentLength >= MAX_FILE_SIZE)
            return Response.status(Response.Status.BAD_REQUEST).build();

        this.organizationLogoService.uploadOrganizationLogo(file);
        return Response.status(200).build();
    }

    /**
     * deletes the organization logo.
     *
     * @return 200 when the image was deleted.
     */
    @POST
    @Path("/delete")
    public Response deleteImage(){
        this.organizationLogoService.deleteOrganizationLogo();
        return Response.status(200).build();
    }
}
