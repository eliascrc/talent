package cr.talent.core.projectPositionHolder.service.impl;

import cr.talent.core.projectPositionHolder.dao.ProjectPositionHolderDao;
import cr.talent.core.projectPositionHolder.service.ProjectPositionHolderService;
import cr.talent.model.ProjectPositionHolder;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

/**
 * Default implementation of the {@link cr.talent.core.projectPositionHolder.service.ProjectPositionHolderService}.
 *
 * @author Elías Calderón
 */
@Service("projectPositionHolderService")
@Transactional
public class ProjectPositionHolderServiceImpl extends CrudServiceImpl<ProjectPositionHolder, String> implements ProjectPositionHolderService {

    @Autowired
    private ProjectPositionHolderDao projectPositionHolderDao;

    public void init() {
        setCrudDao(this.projectPositionHolderDao);
    }

}
