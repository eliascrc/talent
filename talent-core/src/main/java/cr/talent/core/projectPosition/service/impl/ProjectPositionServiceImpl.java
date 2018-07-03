package cr.talent.core.projectPosition.service.impl;

import cr.talent.core.projectPosition.dao.ProjectPositionDao;
import cr.talent.core.projectPosition.service.ProjectPositionService;
import cr.talent.model.Project;
import cr.talent.model.ProjectPosition;
import cr.talent.model.ProjectPositionHolder;
import cr.talent.model.TechnicalResource;
import cr.talent.support.exceptions.NoTechnicalResourceProjectException;
import cr.talent.support.flexjson.JSONSerializerBuilder;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;

/**
 * Default implementation of the {@link cr.talent.core.projectPosition.service.ProjectPositionService}.
 *
 * @author Elías Calderón, Josue Cubero
 */
@Service("projectPositionService")
@Transactional
public class ProjectPositionServiceImpl extends CrudServiceImpl<ProjectPosition, String> implements ProjectPositionService {

    @Autowired
    private ProjectPositionDao projectPositionDao;

    public void init() {
        setCrudDao(this.projectPositionDao);
    }

    /**
     * @see cr.talent.core.projectPosition.service.ProjectPositionService#getTechnicalResourceProjects(TechnicalResource)
     */
    @Override
    public String getTechnicalResourceProjects(TechnicalResource technicalResource) {
        final String noActiveTechnicalResourceProjectsMsg = technicalResource.getFirstName() + " " + technicalResource.getLastName()
                + " does not have any active project";

        if(technicalResource.getProjectPositions().isEmpty()) //ask first for efficiency reasons
            throw new NoTechnicalResourceProjectException(noActiveTechnicalResourceProjectsMsg);

        Set<ProjectPositionHolder> projectPositionHolders = technicalResource.getProjectPositions();

        if(projectPositionHolders.isEmpty()) //ask again after the iteration
            throw new NoTechnicalResourceProjectException(noActiveTechnicalResourceProjectsMsg);

        Set<Project> projects = new HashSet<>();
        for (ProjectPositionHolder activeProjectPositionHolder : projectPositionHolders) {
            projects.add(activeProjectPositionHolder.getProjectPosition().getProject());
        }

        return JSONSerializerBuilder.getTechnicalResourceProjectsSerializer().serialize(projects);

    }
}
