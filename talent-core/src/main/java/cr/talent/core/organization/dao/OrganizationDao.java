package cr.talent.core.organization.dao;

import cr.talent.model.Organization;
import cr.talent.support.dao.CrudDao;

/**
 * Data access object for all the {@link cr.talent.model.Organization} related operations.
 *
 * @author Elías Calderón
 */
public interface OrganizationDao extends CrudDao<Organization, String> {

    Organization getOrganizationByUniqueIdentifier(String uniqueIdentifier);

}
