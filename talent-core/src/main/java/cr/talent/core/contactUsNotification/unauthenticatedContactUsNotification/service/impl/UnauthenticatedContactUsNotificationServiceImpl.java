package cr.talent.core.contactUsNotification.unauthenticatedContactUsNotification.service.impl;

import cr.talent.core.contactUsNotification.unauthenticatedContactUsNotification.dao.UnauthenticatedContactUsNotificationDao;
import cr.talent.core.contactUsNotification.unauthenticatedContactUsNotification.service.UnauthenticatedContactUsNotificationService;
import cr.talent.model.UnauthenticatedContactUsNotification;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Default implementation of the {@link cr.talent.core.contactUsNotification.unauthenticatedContactUsNotification.service.UnauthenticatedContactUsNotificationService}.
 *
 * @author Fabián Roberto Leandro
 */

@Service("unauthenticatedContactUsNotificationService")
@Transactional
public class UnauthenticatedContactUsNotificationServiceImpl extends CrudServiceImpl<UnauthenticatedContactUsNotification, String> implements UnauthenticatedContactUsNotificationService {

    @Autowired
    private UnauthenticatedContactUsNotificationDao unauthenticatedContactUsDao;

    public void init() {
        setCrudDao(this.unauthenticatedContactUsDao);
    }
}

