package cr.talent.support.flexjson;

import cr.talent.model.ProjectPosition;
import cr.talent.model.ProjectPositionHolder;
import cr.talent.model.TechnicalResource;
import flexjson.transformer.AbstractTransformer;

import java.util.HashSet;
import java.util.Set;

/**
 * Transforms a set of project positions to a json array of the project, omitting repeated projects.
 *
 * @author Fabián Roberto Leandro
 */
public class ProjectPositionTransformer extends AbstractInlineTransformer {

    public ProjectPositionTransformer() {
        fieldName = "projects";
    }

    /**
     * If the received object is an instance of a set of project positions, iterate through them write an array of only
     * the names of the projects.
     *
     * @param object
     */
    public void transform(Object object) {

        Set<ProjectPositionHolder> projectPositions;
        try {
            projectPositions = (Set<ProjectPositionHolder>) object;
        } catch (ClassCastException e) {
            this.writeNull();
            return;
        }

        if(projectPositions.isEmpty()) {
            this.writeNull();
            return;
        }

        // Start the projects entry
        this.writeJsonStart();

        // Open an array
        this.getContext().writeOpenArray();

        // Iterate through the project positions, writing each one if it has not been written yet
        Set<String> writtenProjects = new HashSet<>();
        String currentProjectName = null;
        boolean first = true;
        for(ProjectPositionHolder projectPositionHolder : projectPositions) {
            if(first)
                first = false;
            else
                this.getContext().writeComma();

            currentProjectName = projectPositionHolder.getProjectPosition().getProject().getName();
            if(!writtenProjects.contains(currentProjectName)){
                this.getContext().writeQuoted(currentProjectName);
                writtenProjects.add(currentProjectName);
            }
        }

        // Close the projects array
        this.getContext().writeCloseArray();
    }


}
