package cr.talent.core.technicalResource.service.impl;

import cr.talent.core.technicalResource.dao.TechnicalResourceDao;
import cr.talent.core.technicalResource.service.TechnicalResourceService;
import cr.talent.model.TechnicalResource;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Default implementation of the {@link cr.talent.core.technicalResource.service.TechnicalResourceService}.
 *
 * @author Daniel Montes de Oca
 */
@Service("technicalResourceService")
@Transactional
public class TechnicalResourceServiceImpl extends CrudServiceImpl<TechnicalResource, String> implements TechnicalResourceService {

    @Autowired
    private TechnicalResourceDao technicalResourceDao;

    public void init() { setCrudDao(this.technicalResourceDao); }

}
