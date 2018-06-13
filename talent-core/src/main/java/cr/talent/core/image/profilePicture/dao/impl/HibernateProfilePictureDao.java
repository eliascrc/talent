package cr.talent.core.image.profilePicture.dao.impl;

import cr.talent.core.image.profilePicture.dao.ProfilePictureDao;
import cr.talent.model.ProfilePicture;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Hibernate implementation of the {@link ProfilePictureDao}.
 *
 * @author María José Cubero
 */
@Repository("profilePictureDao")
public class HibernateProfilePictureDao extends HibernateCrudDao<ProfilePicture, String> implements ProfilePictureDao {

    @Autowired
    public HibernateProfilePictureDao(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

}
