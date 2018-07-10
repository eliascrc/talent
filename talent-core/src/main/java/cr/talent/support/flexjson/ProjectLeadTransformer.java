package cr.talent.support.flexjson;

import cr.talent.model.LeadPosition;
import cr.talent.model.TechnicalResource;
import flexjson.transformer.AbstractTransformer;

import java.util.HashSet;
import java.util.Set;

/**
 * Used to transform a project's lead history, in order to serialize only the currently active lead.
 *
 * @author Fabián Roberto Leandro
 */
public class ProjectLeadTransformer extends AbstractInlineTransformer {
    private final String idField = "id";
    private final String usernameField = "username";
    private final String firstNameField = "firstName";
    private final String lastNameField = "lastName";


    public ProjectLeadTransformer() {
        fieldName="projectLead";
    }

    /**
     * If the received object is an instance of a set of lead positions, iterate through them to find the currently
     * active lead and write their id, username, and first and last name.
     *
     * @param object
     */
    public void transform(Object object) {

        Set<LeadPosition> projectLeadHistory;
        try {
            projectLeadHistory = (Set<LeadPosition>) object;
        } catch (ClassCastException e) {
            this.writeNull();
            return;
        }

        // Find the currently active lead
        TechnicalResource projectLead = null;
        for (LeadPosition leadPosition : projectLeadHistory) {
            if (leadPosition.getActive())
                projectLead = leadPosition.getLead();
        }

        if (projectLead == null)
            this.writeNull();
        else
            this.writeProjectLeadJson(projectLead);
    }

    /**
     * Writes the project lead as a json object with id, username, first name and last name as values
     *
     * @param projectLead the technical resource to be written in the json
     */
    private void writeProjectLeadJson(TechnicalResource projectLead) {
        this.writeJsonStart();

        // Open the lead object
        this.getContext().writeOpenObject();

        // Write the id
        this.getContext().writeName(this.idField);
        this.getContext().writeQuoted(projectLead.getId());
        this.getContext().writeComma();

        // Write the username
        this.getContext().writeName(this.usernameField);
        this.getContext().writeQuoted(projectLead.getUsername());
        this.getContext().writeComma();

        // Write the first name
        this.getContext().writeName(this.firstNameField);
        this.getContext().writeQuoted(projectLead.getFirstName());
        this.getContext().writeComma();

        // Write the last name
        this.getContext().writeName(this.lastNameField);
        this.getContext().writeQuoted(projectLead.getLastName());

        // Close the lead object
        this.getContext().writeCloseObject();
    }

    /**
     * Set this to true in order to tell flexjson that we will generate the json for the project position
     * This allows us to change the property's name from 'leadHistory' to projectLead
     */
    @Override
    public Boolean isInline() {
        return Boolean.TRUE;
    }
}
