package cr.talent.core.security.humanResourceManager.service.impl;

import cr.talent.core.security.humanResourceManager.dao.HumanResourceManagerDao;
import cr.talent.core.security.humanResourceManager.service.HumanResourceManagerService;
import cr.talent.model.*;
import cr.talent.support.SecurityUtils;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.UUID;

/**
 * Default implementation of the {@link cr.talent.core.security.humanResourceManager.service.HumanResourceManagerService}
 *
 * @author Elías Calderón
 */
@Service("humanResourceManagerService")
@Transactional
public class HumanResourceManagerServiceImpl extends CrudServiceImpl<HumanResourceManager, String> implements HumanResourceManagerService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HumanResourceManagerDao humanResourceManagerDao;

    public void init() {
        setCrudDao(this.humanResourceManagerDao);
    }

    /**
     * Method that creates a System Administrator user by supplying the correct and necessary information
     * to the data access object.
     *
     * @param humanResourceManager The instance of System Administrator that must be created in the data base.
     * @return The created user.
     */
    @Override
    public String create(HumanResourceManager humanResourceManager) {


        humanResourceManager.setUsername(humanResourceManager.getUsername().toLowerCase());
        SecurityUtils.validatePassword(humanResourceManager.getPassword());
        humanResourceManager.setPassword(passwordEncoder.encode(humanResourceManager.getPassword()));
        humanResourceManager.setEnabled(true);
        humanResourceManager.setToken(UUID.randomUUID().toString());

        return super.create(humanResourceManager);
    }

}