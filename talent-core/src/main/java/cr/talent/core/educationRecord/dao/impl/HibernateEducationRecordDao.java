package cr.talent.core.educationRecord.dao.impl;

import cr.talent.core.educationRecord.dao.EducationRecordDao;
import cr.talent.model.EducationRecord;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Hibernate implementation of the {@link cr.talent.core.educationRecord.dao.EducationRecordDao}.
 *
 * @author Elías Calderón
 */
@Repository("educationRecordDao")
public class HibernateEducationRecordDao extends HibernateCrudDao<EducationRecord, String> implements EducationRecordDao {

    @Autowired
    public HibernateEducationRecordDao(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

}
