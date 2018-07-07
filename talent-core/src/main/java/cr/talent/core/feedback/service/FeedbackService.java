package cr.talent.core.feedback.service;

import cr.talent.model.Feedback;
import cr.talent.model.Project;
import cr.talent.model.TechnicalResource;
import cr.talent.support.service.CrudService;

/**
 * Provides business logic services related to {@link cr.talent.model.Feedback} entities.
 *
 * @author Otto Mena, Josué Cubero
 */
public interface FeedbackService extends CrudService<Feedback, String> {

    /**
     * Creates a warning from {@link cr.talent.model.Feedback}.
     *
     * @param observer the related project id.
     * @param observee the technical resource receiving the warning.
     * @param project the related project.
     * @param description the description of the warning.
     *
     * @return a boolean on true if both resources were related to the project or on false if not.
     */
    boolean createWarning(TechnicalResource observer, TechnicalResource observee, Project project, String description);
}
