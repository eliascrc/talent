package cr.talent.core.language.service.impl;

import cr.talent.core.language.dao.LanguageDao;
import cr.talent.core.language.service.LanguageService;
import cr.talent.model.Language;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

/**
 * Default implementation of the {@link cr.talent.core.language.service.LanguageService}.
 *
 * @author Elías Calderón
 */
@Service("languageService")
@Transactional
public class LanguageServiceImpl extends CrudServiceImpl<Language, String> implements LanguageService {

    @Autowired
    private LanguageDao languageDao;

    public void init() {
        setCrudDao(this.languageDao);
    }

}
