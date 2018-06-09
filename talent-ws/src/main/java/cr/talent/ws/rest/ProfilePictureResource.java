package cr.talent.ws.rest;

import com.sun.jersey.multipart.FormDataParam;
import cr.talent.core.image.profilePicture.service.ProfilePictureService;
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
 * Web services to manage images in Amazon S3.
 *
 * @author María José Cubero
 */

@Component
@Scope("request")
@Path("/profilePicture")
public class ProfilePictureResource {

    @Autowired
    ProfilePictureService profilePictureService;

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadImage(@FormDataParam("file") InputStream file) {
        if (StringUtils.isEmpty(file))
            return Response.status(Response.Status.BAD_REQUEST).build();

        this.profilePictureService.uploadProfilePicture(file);
        return Response.status(200).build();
    }

    @POST
    @Path("/delete")
    public Response deleteImage(){
        this.profilePictureService.deleteProfilePicture();
        return Response.status(200).build();
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response updateImage(@FormDataParam("file") InputStream file) {
        if (StringUtils.isEmpty(file))
            return Response.status(Response.Status.BAD_REQUEST).build();

        this.profilePictureService.updateProfilePicture(file);
        return Response.status(200).build();
    }
}
