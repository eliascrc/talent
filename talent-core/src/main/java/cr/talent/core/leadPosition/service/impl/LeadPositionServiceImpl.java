package cr.talent.core.leadPosition.service.impl;

import cr.talent.core.leadPosition.dao.LeadPositionDao;
import cr.talent.core.leadPosition.service.LeadPositionService;
import cr.talent.model.LeadPosition;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Default implementation of the {@link LeadPositionService}.
 *
 * @author Josue Cubero.
 */
@Service("leadPositionService")
@Transactional
public class LeadPositionServiceImpl extends CrudServiceImpl<LeadPosition, String> implements LeadPositionService {

    @Autowired
    private LeadPositionDao leadPositionDao;

    public void init() {
        setCrudDao(this.leadPositionDao);
    }

}
