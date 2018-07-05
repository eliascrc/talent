package cr.talent.ws.rest;

import cr.talent.core.educationRecord.service.EducationRecordService;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.model.EducationRecord;
import cr.talent.model.TechnicalResource;
import cr.talent.support.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Resource for education record related operations
 *
 * @author Daniel Montes de Oca
 */
@Component
@Scope("request")
@Path("/educationRecord")
public class EducationRecordResource {

    @Autowired
    EducationRecordService educationRecordService;

    @Autowired
    TechnicalResourceService technicalResourceService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEducationRecords(@QueryParam("technicalResourceId") String technicalResourceId) {
        TechnicalResource loggedInUser = SecurityUtils.getLoggedInTechnicalResource();

        TechnicalResource technicalResource = technicalResourceService.findById(technicalResourceId);

        if (technicalResource == null)
            return Response.status(Response.Status.NOT_FOUND).build();


        Set<EducationRecord> educationRecords = technicalResource.getEducationRecords();

        if (educationRecords == null || educationRecords.size() == 0)
            return Response.noContent().build();


        return null;
    }

}
