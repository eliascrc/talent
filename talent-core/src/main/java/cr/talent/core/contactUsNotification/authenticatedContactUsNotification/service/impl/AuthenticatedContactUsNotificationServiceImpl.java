package cr.talent.core.contactUsNotification.authenticatedContactUsNotification.service.impl;

import cr.talent.core.contactUsNotification.authenticatedContactUsNotification.dao.AuthenticatedContactUsNotificationDao;
import cr.talent.core.contactUsNotification.authenticatedContactUsNotification.service.AuthenticatedContactUsNotificationService;
import cr.talent.model.AuthenticatedContactUsNotification;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Default implementation of the {@link cr.talent.core.contactUsNotification.authenticatedContactUsNotification.service.AuthenticatedContactUsNotificationService}.
 *
 * @author Fabián Roberto Leandro
 */

@Service("authenticatedContactUsNotificationService")
@Transactional
public class AuthenticatedContactUsNotificationServiceImpl extends CrudServiceImpl<AuthenticatedContactUsNotification, String> implements AuthenticatedContactUsNotificationService {

    @Autowired
    private AuthenticatedContactUsNotificationDao authenticatedContactUsDao;

    public void init() {
        setCrudDao(this.authenticatedContactUsDao);
    }
}
