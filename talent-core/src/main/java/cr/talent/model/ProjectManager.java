package cr.talent.model;

import java.util.List;

/**
 * Class that represents a Project Manager within the Talent system. It contains the list of project
 * management positions that the Project Manager has occupied. It also contains the information inherited from
 * {@link cr.talent.model.TechnicalResourceManager} class.
 *
 * @author Elías Calderón
 */
public class ProjectManager extends TechnicalResourceManager {

    /**
     * A list with the project management positions that the Project Manager has occupied in the organization.
     */
    private List<ProjectManagerPosition> projectManagerPositions;

    public List<ProjectManagerPosition> getProjectManagerPositions() {
        return projectManagerPositions;
    }

    public void setProjectManagerPositions(List<ProjectManagerPosition> projectManagerPositions) {
        this.projectManagerPositions = projectManagerPositions;
    }
}
