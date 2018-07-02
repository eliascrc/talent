package cr.talent.core.careerPath.service.impl;

import cr.talent.core.careerPath.dao.CareerPathDao;
import cr.talent.core.careerPath.service.CareerPathService;
import cr.talent.model.CareerPath;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Default implementation of the {@link cr.talent.core.careerPath.service.CareerPathService}
 *
 * @author Fabián Roberto Leandro
 */
@Service("careerPathService")
@Transactional
public class CareerPathServiceImpl extends CrudServiceImpl<CareerPath, String> implements CareerPathService {

    @Autowired
    CareerPathDao careerPathDao;

    public void init() {
        setCrudDao(this.careerPathDao);
    }
}
