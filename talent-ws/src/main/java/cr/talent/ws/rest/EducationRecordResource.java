package cr.talent.ws.rest;

import cr.talent.core.educationRecord.service.EducationRecordService;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.model.EducationRecord;
import cr.talent.model.TechnicalResource;
import cr.talent.support.SecurityUtils;
import cr.talent.support.flexjson.JSONSerializerBuilder;
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
@Path("technicalResource/educationRecord")
public class EducationRecordResource {

    @Autowired
    EducationRecordService educationRecordService;

    @Autowired
    TechnicalResourceService technicalResourceService;

    /**
     * Endpoint that returns the information of a technical resource regarding education records
     *
     * @param technicalResourceId the id of the technical resource of whom we want their education records
     * @return  404 if the resource was not found or if they belong to another organization
     *          204 if the resource has no education records
     *          200 and a JSON with the education records information of the resource if the request is successful
     */
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEducationRecords(@QueryParam("technicalResourceId") String technicalResourceId) {
        TechnicalResource loggedInTechnicalResource = SecurityUtils.getLoggedInTechnicalResource();

        TechnicalResource technicalResource = technicalResourceService.findById(technicalResourceId);

        if (technicalResource == null || !technicalResource.getOrganization().equals(loggedInTechnicalResource.getOrganization()))
            return Response.status(Response.Status.NOT_FOUND).build();


        Set<EducationRecord> educationRecords = this.educationRecordService.getEducationRecords(technicalResource);

        if (educationRecords == null || educationRecords.isEmpty())
            return Response.noContent().build();

        return Response.ok().entity(JSONSerializerBuilder.getTechnicalResourceEducationRecordsSerializer()
                        .serialize(educationRecords)).build();
    }

}
