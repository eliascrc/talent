package cr.talent.ws.rest;

import com.sun.jersey.core.header.FormDataContentDisposition;
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
 * Web services to manage profile pictures in Amazon S3.
 *
 * @author María José Cubero
 */

@Component
@Scope("request")
@Path("/profilePicture")
public class ProfilePictureResource {

    private static long MAX_FILE_SIZE = 5242880;

    @Autowired
    ProfilePictureService profilePictureService;

    /**
     * uploads the profile picture.
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
        if (StringUtils.isEmpty(file) || contentLength >= MAX_FILE_SIZE)
            return Response.status(Response.Status.BAD_REQUEST).build();

        this.profilePictureService.uploadProfilePicture(file);
        return Response.status(200).build();
    }

    /**
     * deletes the profile picture.
     *
     * @return 200 when the image was deleted.
     */
    @POST
    @Path("/delete")
    public Response deleteImage(){
        this.profilePictureService.deleteProfilePicture();
        return Response.status(200).build();
    }
}
