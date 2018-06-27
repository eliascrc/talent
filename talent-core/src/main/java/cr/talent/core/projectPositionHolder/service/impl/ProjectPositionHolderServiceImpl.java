package cr.talent.core.projectPositionHolder.service.impl;

import cr.talent.core.projectPositionHolder.service.ProjectPositionHolderService;
import cr.talent.model.ProjectPosition;
import cr.talent.model.ProjectPositionHolder;
import cr.talent.model.TechnicalResource;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Hibernate implementation of the {@link cr.talent.core.projectPositionHolder.dao.ProjectPositionHolderDao}.
 *
 * @author Daniel Montes de Oca
 */
@Service("projectPositionHolderService")
@Transactional
public class ProjectPositionHolderServiceImpl extends CrudServiceImpl<ProjectPositionHolder, String> implements ProjectPositionHolderService {

    /**
     * @see cr.talent.core.projectPositionHolder.service.ProjectPositionHolderService#assignProjectPosition(TechnicalResource, TechnicalResource, ProjectPosition)
     */
    @Override
    public void assignProjectPosition(TechnicalResource assigner, TechnicalResource assignee,
                                      ProjectPosition projectPosition){

    }

}
