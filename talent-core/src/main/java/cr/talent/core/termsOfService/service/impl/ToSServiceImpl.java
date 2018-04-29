package cr.talent.core.termsOfService.service.impl;

import cr.talent.core.termsOfService.dao.ToSDao;
import cr.talent.core.termsOfService.service.ToSService;
import cr.talent.model.TermsOfService;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Default implementation of the {@link ToSService}
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

    /**
     * Gets the currently active version of the system's Terms of Service via the data access object of the
     * service.
     * @return The active TermsOfService
     */
    @Override
    public TermsOfService getActiveTermsOfService() {
        return this.toSDao.getActiveTermsOfService();
    }

    /**
     * Gets the currently active content of the system's Terms of Service via the data access object of the
     * service.
     * @return The active TermsOfService content
     */
    @Override
    public String getActiveTermsOfServiceContent() {
        return this.toSDao.getActiveTermsOfServiceContent();
    }

    /**
     * Sets the start date of the TermsOfService being created. It then
     * calls the data access object create() method to create it in the database.
     * @param termsOfService
     * @return
     */
    @Override
    public String create(TermsOfService termsOfService) {
        termsOfService.setStartDate(new Date());
        return super.create(termsOfService);
    }

}
