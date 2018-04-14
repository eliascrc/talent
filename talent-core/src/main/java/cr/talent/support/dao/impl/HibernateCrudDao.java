package cr.talent.support.dao.impl;

import cr.talent.model.BasicEntity;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

/**
 * Hibernate implementation of CrudDao interface.
 *
 * All Hibernate implemented DAOs must extends this class to provide full reusable CRUD support.
 *
 * @author Rodrigo A. Bartels
 */
@Transactional
public abstract class HibernateCrudDao<ModelObjectType extends BasicEntity, KeyType extends Serializable> {

    protected final transient Logger logger = LoggerFactory.getLogger(getDomainClass());

    private Class domainClass;

    private SessionFactory sessionFactory;

    /**
     * Retrieves the class that fills the <code>ModelObjectType</code> generic
     * placeholder.
     *
     * @return Class instance.
     */
    protected Class getDomainClass() {
        if (domainClass == null) {
            ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
            domainClass = (Class) thisType.getActualTypeArguments()[0];
        }
        return domainClass;
    }

    /**
     * @see cr.talent.support.dao.CrudDao#create(cr.talent.model.BasicEntity)
     */
    @SuppressWarnings("unchecked")
    public KeyType create(ModelObjectType entity) {
        entity.setEntityCreationTimestamp(new Date());
        entity.setLastUpdatedTimestamp(new Date());
        entity.setEntityVersion(1);
        return (KeyType) getSessionFactory().getCurrentSession().save(entity);
    }

    /**
     * @see cr.talent.support.dao.CrudDao#update(cr.talent.model.BasicEntity)
     */
    public void update(ModelObjectType entity) {
        entity.setLastUpdatedTimestamp(new Date());
        entity.setEntityVersion(entity.getEntityVersion() + 1);
        getSessionFactory().getCurrentSession().update(entity);
    }

    /**
     * @see cr.talent.support.dao.CrudDao#remove(cr.talent.model.BasicEntity)
     */
    public void remove(ModelObjectType entity) {
        getSessionFactory().getCurrentSession().delete(entity);
    }

    /**
     * @see cr.talent.support.dao.CrudDao#findById(Object)
     */
    @SuppressWarnings("unchecked")
    public ModelObjectType findById(KeyType entityId) {
        Class type = getDomainClass();
        try {
            return (ModelObjectType) getSessionFactory().getCurrentSession().get(type, entityId);
        } catch (ObjectRetrievalFailureException e) {
            if (logger.isWarnEnabled()) {
                logger.warn(String.format("object instance of %s identified by %s not found in the database",
                        type.getName(), entityId));
            }
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<ModelObjectType> getAll() {
        Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(this.domainClass);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<ModelObjectType>) criteria.list();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

