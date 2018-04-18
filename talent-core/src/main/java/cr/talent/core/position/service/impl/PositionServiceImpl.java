package cr.talent.core.position.service.impl;

import cr.talent.core.position.dao.PositionDao;
import cr.talent.core.position.service.PositionService;
import cr.talent.model.Position;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

/**
 * Default implementation of the {@link cr.talent.core.position.service.PositionService}.
 *
 * @author Elías Calderón
 */
@Service("positionService")
@Transactional
public class PositionServiceImpl extends CrudServiceImpl<Position, String> implements PositionService {

    @Autowired
    private PositionDao positionDao;

    public void init() {
        setCrudDao(this.positionDao);
    }

}
