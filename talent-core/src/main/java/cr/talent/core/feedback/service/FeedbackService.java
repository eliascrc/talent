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
     * Creates a kudo from {@link cr.talent.model.Feedback}.
     *
     * @param observer the related project id.
     * @param observee the technical resource receiving the kudo.
     * @param project the related project.
     * @param description the description of the kudo.
     */
    void createKudo(TechnicalResource observer, TechnicalResource observee, Project project, String description);
}
