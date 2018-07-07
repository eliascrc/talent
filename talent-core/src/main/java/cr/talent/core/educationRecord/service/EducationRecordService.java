package cr.talent.core.educationRecord.service;

import cr.talent.model.EducationRecord;
import cr.talent.model.TechnicalResource;
import cr.talent.support.service.CrudService;

import java.util.Set;

/**
 * Provides business logic services related to {@link cr.talent.model.EducationRecord} entities.
 *
 * @author Elías Calderón
 */
public interface EducationRecordService extends CrudService<EducationRecord, String> {

    /**
     * Used to obtain the education records of a technical resource
     * @param technicalResource the technical resource of whom we wil obtain their education records
     */
    Set<EducationRecord> getEducationRecords(TechnicalResource technicalResource);

}
