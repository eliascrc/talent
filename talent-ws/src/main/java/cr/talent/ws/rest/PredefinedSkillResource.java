package cr.talent.ws.rest;

import cr.talent.core.skill.service.SkillService;
import cr.talent.model.Skill;
import cr.talent.support.exceptions.AlreadyCreatedPredefinedSkillException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Resource with a POST endpoint that creates a new predefined skill
 *
 * @author Elías Calderón
 */
@Component
@Scope("request")
@Path("/admin/skill")
public class PredefinedSkillResource {

    @Autowired
    private SkillService skillService;

    /**
     * Receives the request for creating a new predefined skill.
     * @param name the predefined skill's name.
     * @return 200 if the predefined skill is created correctly,
     *          400 if the name is null or an empty string,
     *          409 if the predefined skill name is already registered in the system.
     */
    @POST
    @Path("/create")
    public Response createPredefinedSkill(
            @FormParam("name") String name) {

        if (name == null || name.equals(""))
            return Response.status(Response.Status.BAD_REQUEST).build(); //Form Parameters should not be null or empty

        Skill skill = new Skill();
        skill.setName(name);

        try {

            this.skillService.createPredefinedSkill(skill);
            return Response.ok().build();

        } catch (AlreadyCreatedPredefinedSkillException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }

    }

}
