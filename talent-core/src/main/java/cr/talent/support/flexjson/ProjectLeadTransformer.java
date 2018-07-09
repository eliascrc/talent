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
public class ProjectLeadTransformer extends AbstractTransformer {
    private final String projectLeadField = "projectLead";
    private final String idField = "id";
    private final String usernameField = "username";
    private final String firstNameField = "firstName";
    private final String lastNameField = "lastName";


    public ProjectLeadTransformer() {
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

    private void writeJsonStart() {
        // Write a comma if necessary
        if (!getContext().peekTypeContext().isFirst())
            getContext().writeComma();

        // Write the project lead field name
        getContext().writeName(projectLeadField);
    }

    private void writeNull() {
        writeJsonStart();

        // write the "null" string
        getContext().write("null");
    }

    private void writeProjectLeadJson(TechnicalResource projectLead) {
        writeJsonStart();

        // Open the lead object
        getContext().writeOpenObject();

        // Write the id
        getContext().writeName(idField);
        getContext().writeQuoted(projectLead.getId());
        getContext().writeComma();

        // Write the username
        getContext().writeName(usernameField);
        getContext().writeQuoted(projectLead.getUsername());
        getContext().writeComma();

        // Write the first name
        getContext().writeName(firstNameField);
        getContext().writeQuoted(projectLead.getFirstName());
        getContext().writeComma();

        // Write the last name
        getContext().writeName(lastNameField);
        getContext().writeQuoted(projectLead.getLastName());

        // Close the lead object
        getContext().writeCloseObject();
    }

    /**
     * Set this to true in order to tell flexjson that we will generate the json for the project position
     * This allows us to change the property's name from 'object' to projectLead
     */
    @Override
    public Boolean isInline() {
        return Boolean.TRUE;
    }
}
