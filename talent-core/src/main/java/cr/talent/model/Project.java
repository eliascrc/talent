package cr.talent.model;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Date;

/**
 * Class that represents a project within the Talent system.
 * It contains the project name, dates, capabilities, project manager history, status
 * and the information inherited from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Elías Calderón
 */
public class Project extends BasicEntity {

    /**
     * The name of the project.
     */
    private String name;

    /**
     * The date in which the project started.
     */
    private Date startDate;

    /**
     * The date in which the project finished.
     */
    private Date endDate;

    /**
     * A list with the capabilities of the project.
     */
    private ArrayList<ProjectCapability> projectCapabilities;

    /**
     * A stack with the history of project manager's throughout the life time of the project. The top on the
     * stack is the current or last project manager a project had.
     */
    private Stack<ProjectManagerPosition> projectManagerHistory;

    /**
     * The state that the project currently has.
     */
    private ProjectState state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public ArrayList<ProjectCapability> getProjectCapabilities() {
        return projectCapabilities;
    }

    public void setProjectCapabilities(ArrayList<ProjectCapability> projectCapabilities) {
        this.projectCapabilities = projectCapabilities;
    }

    public ProjectState getState() {
        return state;
    }

    public void setState(ProjectState state) {
        this.state = state;
    }

    public Stack<ProjectManagerPosition> getProjectManagerHistory() {
        return projectManagerHistory;
    }

    public void setProjectManagerHistory(Stack<ProjectManagerPosition> projectManagerHistory) {
        this.projectManagerHistory = projectManagerHistory;
    }

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if ( o instanceof Project){
            Project project = (Project) o;
            result = (this.name == null ? project.getName() == null : this.name.equals(project.getName()));
        }
        return result;
    }

    @Override
    protected int onHashCode(int result) {
        final int prime = 23;
        result = prime * result + (this.name == null ? 0 : this.name.hashCode());
        return result;
    }

}