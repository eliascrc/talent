package cr.talent.core.DAOTests;

import cr.talent.core.termsOfService.dao.impl.HibernateToSDao;
import cr.talent.model.TermsOfService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * This class will test Terms of Service DAO layer using an in memory database.
 * @author Josue Cubero.
 */
@ContextConfiguration(locations = "classpath:talent-persistence.spring-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DaoTest {

    @Autowired
    private SessionFactory sessionFactory;

    private HibernateToSDao hibernateToSDao;
    private Session currentSession;
    private TermsOfService termsOfService;

    //Attributes to build and query a terms of service object
    private static final Date ENTITY_CREATION_TIMESTAMP = new Date();
    private static final Date LAST_UPDATED_TIMESTAMP = new Date();
    private static final Date START_DATE = new Date();
    private static final long ENTITY_VERSION = 1l;
    private static final String TERMS_OF_SERVICE_CONTENT = "Lorem ipsum";
    private static final Date END_DATE = new Date();
    private static final boolean IS_ACTIVE = true;

    @Before
    public void before() {
        this.createTermsOfService();
        this.hibernateToSDao = new HibernateToSDao(this.sessionFactory);
        this.currentSession = this.hibernateToSDao.getSessionFactory().openSession();
    }

    @Test
    public void shouldHaveASessionFactory() {
        assertNotNull(this.hibernateToSDao.getSessionFactory());
    }

    @Test
    public void shouldHaveNoObjectsAtStart() {
        List<?> results = this.currentSession.createQuery("from TermsOfService").list();
        assertTrue(results.isEmpty());
    }

    @Test
    public void shouldBeAbleToPersistAnObject() {
        Transaction transaction = currentSession.beginTransaction();

        this.currentSession.persist(this.termsOfService);
        this.currentSession.flush();

        transaction.commit();
    }

    @Test
    public void assertSuccessfulQueries(){
        Timestamp timestamp;

        //Tests for successfull queries
        timestamp = new Timestamp(ENTITY_CREATION_TIMESTAMP.getTime());
        assertEquals(1, this.currentSession.createQuery("from TermsOfService where entityCreationTimestamp = '" + timestamp + "'").list().size());

        timestamp = new Timestamp(LAST_UPDATED_TIMESTAMP.getTime());
        assertEquals(1, this.currentSession.createQuery("from TermsOfService where lastUpdatedTimestamp = '" + timestamp + "'").list().size());

        assertEquals(1, this.currentSession.createQuery("from TermsOfService where entityVersion = " + ENTITY_VERSION).list().size());
        assertEquals(1, this.currentSession.createQuery("from TermsOfService where termsOfServiceContent = '" + TERMS_OF_SERVICE_CONTENT + "'").list().size());
        assertEquals(1, this.currentSession.createQuery("from TermsOfService where isActive = " + IS_ACTIVE).list().size());

        timestamp = new Timestamp(END_DATE.getTime());
        assertEquals(1, this.currentSession.createQuery("from TermsOfService where endDate = '" + timestamp + "'").list().size());

        timestamp = new Timestamp(START_DATE.getTime());
        assertEquals(1, this.currentSession.createQuery("from TermsOfService where startDate = '" + timestamp + "'").list().size());
    }

    @Test
    public void assertUnsuccessfulQuery(){
        assertEquals(0, this.currentSession.createQuery("from TermsOfService where termsOfServiceContent = 'Fake lorem'").list().size());
    }

    private void createTermsOfService(){
        this.termsOfService = new TermsOfService();
        this.termsOfService.setEntityCreationTimestamp(ENTITY_CREATION_TIMESTAMP);
        this.termsOfService.setLastUpdatedTimestamp(LAST_UPDATED_TIMESTAMP);
        this.termsOfService.setEntityVersion(ENTITY_VERSION);
        this.termsOfService.setToSContent(TERMS_OF_SERVICE_CONTENT);
        this.termsOfService.setEndDate(END_DATE);
        this.termsOfService.setActive(IS_ACTIVE);
        this.termsOfService.setStartDate(START_DATE);
    }

}
