package cr.talent.core.educationRecord.service.impl;

import cr.talent.core.educationRecord.dao.EducationRecordDao;
import cr.talent.core.educationRecord.service.EducationRecordService;
import cr.talent.model.EducationRecord;
import cr.talent.model.TechnicalResource;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Set;

/**
 * Default implementation of the {@link cr.talent.core.educationRecord.service.EducationRecordService}.
 *
 * @author Elías Calderón
 */
@Service("educationRecordService")
@Transactional
public class EducationRecordServiceImpl extends CrudServiceImpl<EducationRecord, String> implements EducationRecordService {

    @Autowired
    private EducationRecordDao educationRecordDao;

    public void init() {
        setCrudDao(this.educationRecordDao);
    }

    /**
     * @see cr.talent.core.educationRecord.service.EducationRecordService#getEducationRecords(TechnicalResource) 
     */
    @Override
    public Set<EducationRecord> getEducationRecords(TechnicalResource technicalResource) {
        return technicalResource.getEducationRecords();
    }
}
