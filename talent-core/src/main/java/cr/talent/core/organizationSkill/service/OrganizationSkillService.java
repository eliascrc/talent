package cr.talent.core.organizationSkill.service;

import cr.talent.model.Organization;
import cr.talent.model.OrganizationSkill;
import cr.talent.model.TechnicalResource;
import cr.talent.support.service.CrudService;

import java.util.List;

/**
 * Provides business logic services related to {@link cr.talent.model.OrganizationSkill} entities.
 *
 * @author Elías Calderón, Josue Cubero
 */
public interface OrganizationSkillService extends CrudService<OrganizationSkill, String> {

    /**
     * Persists a list of {@link cr.talent.model.OrganizationSkill}.
     * @param skills the skills that will try to be assigned to the technical resource.
     * @param organization the technical resource organization.
     * @param technicalResource the technical resource.
     */
    void assignSkillToTechnicalResource(List<String> skills, Organization organization, TechnicalResource technicalResource);

}
