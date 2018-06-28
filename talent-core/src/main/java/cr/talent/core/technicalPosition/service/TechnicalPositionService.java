package cr.talent.core.technicalPosition.service;

import cr.talent.model.Organization;
import cr.talent.model.TechnicalPosition;
import cr.talent.model.TechnicalResource;
import cr.talent.support.service.CrudService;

import java.util.List;

/**
 * Provides business logic services related to {@link cr.talent.model.TechnicalPosition} entities.
 *
 * @author Elías Calderón, Fabián Roberto Leandro
 */
public interface TechnicalPositionService extends CrudService<TechnicalPosition, String> {

    /**
     * Persists a {@link cr.talent.model.TechnicalPosition} with the respective business logic..
     * @param technicalPosition the technical position that will try to be assigned to the technical resource.
     * @param organization the technical resource's organization.
     * @param technicalResource the technical resource.
     */
    void assignTechnicalPositionToTechnicalResource(String technicalPosition, Organization organization, TechnicalResource technicalResource);
}
