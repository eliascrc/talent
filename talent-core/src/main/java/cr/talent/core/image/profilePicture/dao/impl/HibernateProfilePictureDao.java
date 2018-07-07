package cr.talent.core.image.profilePicture.dao.impl;

import cr.talent.core.image.profilePicture.dao.ProfilePictureDao;
import cr.talent.model.ProfilePicture;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * @see cr.talent.core.image.profilePicture.dao.ProfilePictureDao#findProfilePictureByLink(String)
     */
    @Override
    public ProfilePicture findProfilePictureByLink(String link) {
        Query query = super.getSessionFactory().getCurrentSession()
                .createQuery("from ProfilePicture where link = :link");

        query.setParameter("link", link);
        List<ProfilePicture> profilePictureResult = (List<ProfilePicture>)query.list();

        return DataAccessUtils.singleResult(profilePictureResult);
    }
}
