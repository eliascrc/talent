package cr.talent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Class that represents a project manager position within the Talent system.
 * It contains the project start date, end date, project and project manager.
 *
 * @author María José Cubero
 */
@Entity
@Table(name = "project_manager_position")
public class ProjectManagerPosition extends BasicEntity{

    /**
     * Start date of the project.
     */
    @Column(name = "start_date", nullable = true)
    private Date startDate;

    /**
     * End date of the project.
     */
    @Column(name = "end_date")
    private Date endDate;

    /**
     * The project of the project manager position.
     */
    private Project project;

    /**
     * The project manager of this position.
     */
    private ProjectManager projectManager;

    public ProjectManagerPosition (){}

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if ( o instanceof ProjectManagerPosition){
            ProjectManagerPosition projectManagerPosition = (ProjectManagerPosition) o;
            result = (this.project == null ? projectManagerPosition.getProject() == null : this.project.equals(projectManagerPosition.getProject())
                    && this.projectManager == null ? projectManagerPosition.getProjectManager() == null : this.projectManager.equals(projectManagerPosition.getProjectManager()));
        }
        return result;
    }

    @Override
    protected int onHashCode(int result) {
        final int prime = 23;
        result = prime * result + (this.project == null ? 0 : this.project.hashCode());
        result = prime * result + (this.projectManager == null ? 0 : this.projectManager.hashCode());
        return result;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ProjectManager getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }

}
