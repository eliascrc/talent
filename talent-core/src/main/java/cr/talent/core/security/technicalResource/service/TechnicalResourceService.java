package cr.talent.core.security.technicalResource.service;


import cr.talent.support.service.CrudService;
import cr.talent.model.TechnicalResource;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Provides business logic services related to {@link TechnicalResource} entities.
 *
 * @author Josué León Sarkis
 */
public interface TechnicalResourceService extends CrudService<TechnicalResource, String>, UserDetailsService {

    TechnicalResource getTechnicalResourceByUsername(String username);

}
