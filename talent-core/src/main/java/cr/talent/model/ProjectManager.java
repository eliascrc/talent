package cr.talent.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

/**
 * Class that represents a Project Manager within the Talent system. It contains the list of project
 * management positions that the Project Manager has occupied. It also contains the information inherited from
 * {@link cr.talent.model.TechnicalResourceManager} class.
 *
 * @author María José Cubero
 */
@Entity
@Table(name = "project_manager")
public class ProjectManager extends TechnicalResourceManager {

    /**
     * A list with the project management positions that the Project Manager has occupied in the organization.
     */
    private Set<ProjectManagerPosition> projectManagerPositions;

    public ProjectManager(){}

    public Set<ProjectManagerPosition> getProjectManagerPositions() {
        return projectManagerPositions;
    }

    public void setProjectManagerPositions(Set<ProjectManagerPosition> projectManagerPositions) {
        this.projectManagerPositions = projectManagerPositions;
    }
}
