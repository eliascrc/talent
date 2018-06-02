package cr.talent.core.contactEmail.service.impl;

import cr.talent.core.contactEmail.dao.ContactEmailDao;
import cr.talent.core.contactEmail.service.ContactEmailService;
import cr.talent.model.ContactEmail;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Default implementation of the {@link cr.talent.core.contactEmail.service.ContactEmailService}.
 *
 * @author Fabián Roberto Leandro
 */

@Service("contactEmailService")
@Transactional
public class ContactEmailServiceImpl extends CrudServiceImpl<ContactEmail, String> implements ContactEmailService {

    @Autowired
    private ContactEmailDao contactEmailDao;

    public void init() {
        setCrudDao(this.contactEmailDao);
    }
}
