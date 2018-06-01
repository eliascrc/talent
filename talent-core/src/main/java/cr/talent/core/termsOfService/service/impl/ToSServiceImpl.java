package cr.talent.core.termsOfService.service.impl;

import cr.talent.core.termsOfService.dao.ToSDao;
import cr.talent.core.termsOfService.service.ToSService;
import cr.talent.model.Platform;
import cr.talent.model.TermsOfService;
import cr.talent.support.exceptions.NoActiveTermsOfServiceException;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Default implementation of the {@link cr.talent.core.termsOfService.service.ToSService}
 *
 * @author Josué León Sarkis
 */
@Service
@Transactional
public class ToSServiceImpl extends CrudServiceImpl<TermsOfService, String> implements ToSService {

    @Autowired
    private ToSDao toSDao;

    /**
     * Initialization method that sets the respective fields during bean instantiation.
     */
    public void init(){
        setCrudDao(this.toSDao);
    }

    @Override
    public TermsOfService getActiveTermsOfService(Platform platform) {
        final String noActiveTermsOfService = "There is no currently active terms of service content for the " +
                "requested platform";

        if(this.toSDao.getActiveTermsOfService(platform) == null)
            throw new NoActiveTermsOfServiceException(noActiveTermsOfService);

        return this.toSDao.getActiveTermsOfService(platform);
    }

}
