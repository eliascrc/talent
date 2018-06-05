package cr.talent.ws.rest;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import cr.talent.core.image.profilePicture.service.ProfilePictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Web services to manage images in Amazon S3.
 *
 * @author María José Cubero
 */

@Component
@Scope("request")
@Path("/image")
public class ImageResource {

    @Autowired
    ProfilePictureService profilePictureService;

    @POST
    @Path("/upload")
    public Response uploadImage(@FormParam("filePath") String filePath) {
        if (StringUtils.isEmpty(filePath))
            return Response.status(Response.Status.BAD_REQUEST).build();

        this.profilePictureService.uploadProfilePicture(filePath);
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
    public Response updateImage(@FormParam("filePath") String filePath){
        if (StringUtils.isEmpty(filePath))
            return Response.status(Response.Status.BAD_REQUEST).build();

        this.profilePictureService.updateProfilePicture(filePath);
        return Response.status(200).build();
    }

    @GET
    @Path("/get/")
    public Response getImage(@QueryParam("link") String link){
        this.profilePictureService.getProfilePicture(link);
        return Response.status(200).build();
    }
}
