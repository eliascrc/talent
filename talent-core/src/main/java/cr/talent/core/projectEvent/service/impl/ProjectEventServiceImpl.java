package cr.talent.core.projectEvent.service.impl;

import cr.talent.core.projectEvent.dao.ProjectEventDao;
import cr.talent.core.projectEvent.service.ProjectEventService;
import cr.talent.model.ProjectEvent;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

/**
 * Default implementation of the {@link cr.talent.core.projectEvent.service.ProjectEventService}.
 *
 * @author Elías Calderón
 */
@Service("projectEventService")
@Transactional
public class ProjectEventServiceImpl extends CrudServiceImpl<ProjectEvent, String> implements ProjectEventService {

    @Autowired
    private ProjectEventDao projectEventDao;

    public void init() {
        setCrudDao(this.projectEventDao);
    }

}
