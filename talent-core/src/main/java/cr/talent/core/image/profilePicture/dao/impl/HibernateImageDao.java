package cr.talent.core.image.profilePicture.dao.impl;

import cr.talent.core.image.profilePicture.dao.DatabaseImageDao;
import cr.talent.model.ProfilePicture;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Hibernate implementation of the {@link DatabaseImageDao}.
 *
 * @author María José Cubero
 */
@Repository("databaseImageDao")
public class HibernateImageDao  extends HibernateCrudDao<ProfilePicture, String> implements DatabaseImageDao {

    @Autowired
    public HibernateImageDao(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    public void createImage(String TechnicalResource){
    }

    public void deleteImage(){

    }

    public void getImage(){

    }



}
