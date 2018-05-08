package cr.talent.ws.rest;

import cr.talent.core.skill.service.SkillService;
import cr.talent.model.PredefinedSkill;
import cr.talent.support.exceptions.AlreadyCreatedPredefinedSkillException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Resource with a POST endpoint that creates a new skill
 *
 * @author Elías Calderón
 */
@Component
@Scope("request")
@Path("/predefinedSkill")
public class PredefinedSkillResource {

    @Autowired
    SkillService skillService;

    /**
     * Receives the request for creating a new predefined skill.
     * @param name the predefined skill's name
     * @return 200 if the predefined skill is created correctly, 400 if the name is null or an empty string,
     * 409 if the predefined skill name is already registered in the system.
     */
    @POST
    @Path("/create")
    public Response createPredefinedSkill(
            @FormParam("name") String name) {

        if (name == null || name.equals(""))
            return Response.status(Response.Status.BAD_REQUEST).build();

        PredefinedSkill skill = new PredefinedSkill();
        skill.setName(name);
        try {
            this.skillService.createPredefinedSkill(skill);
        } catch (AlreadyCreatedPredefinedSkillException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }

        return Response.ok().build();
    }

}
