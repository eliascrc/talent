package cr.talent.model;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;

/**
 * Class that allows to test the projectManagerPosition methods to know all the different paths they could take.
 *  This class contains the test of the inherited methods ans attributes
 *  from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Josue Cubero
 */
public class LeadPositionTest {

    private static final String ID = "1234";
    private static final String ID2 = "12345";
    private static final Project PROJECT = mock(Project.class);
    private static final TechnicalResource LEAD = mock(TechnicalResource.class);
    private static final Project PROJECT2 = mock(Project.class);
    private static final TechnicalResource LEAD2 = mock(TechnicalResource.class);

    @Test
    public void coreTest() {

        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;
        Date startDate = new Date();
        Date endDate = new Date();

        // Verify Constructor
        LeadPosition leadPosition = new LeadPosition();

        // Verify the sets
        leadPosition.setId(ID);
        leadPosition.setEntityCreationTimestamp(entityCreationTimestamp);
        leadPosition.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        leadPosition.setEntityVersion(entityVersion);
        leadPosition.setEndDate(endDate);
        leadPosition.setStartDate(startDate);
        leadPosition.setProject(PROJECT);
        leadPosition.setLead(LEAD);

        // Verify the gets
        assertEquals(ID, leadPosition.getId());
        assertEquals(entityCreationTimestamp, leadPosition.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, leadPosition.getLastUpdatedTimestamp());
        assertEquals(entityVersion, leadPosition.getEntityVersion());
        assertEquals(endDate, leadPosition.getEndDate());
        assertEquals(startDate, leadPosition.getStartDate());
        assertEquals(PROJECT, leadPosition.getProject());
        assertEquals(LEAD, leadPosition.getLead());
    }

    //ON EQUALS TESTS.

    @Test
    public void testEqualForSameObject() {
        LeadPosition leadPosition1 = new LeadPosition();

        assertTrue(leadPosition1.equals(leadPosition1));
    }

    @Test
    public void testEqualForDifferentClass() {
        LeadPosition leadPosition1 = new LeadPosition();

        assertFalse(leadPosition1.equals(new Object()));
    }

    @Test
    public void testEqualForPersistentProjectManagerPosition() {
        LeadPosition leadPosition1 = new LeadPosition();
        leadPosition1.setId(ID);

        LeadPosition leadPosition2 = new LeadPosition();
        leadPosition2.setId(ID);

        assertTrue(leadPosition1.equals(leadPosition2));
    }

    @Test
    public void testNonEqualForPersistentProjectManagerPosition() {
        LeadPosition leadPosition1 = new LeadPosition();
        leadPosition1.setId(ID);

        LeadPosition leadPosition2 = new LeadPosition();
        leadPosition2.setId(ID2);

        assertFalse(leadPosition1.equals(leadPosition2));
    }

    @Test
    public void testEqualForNonPersistentProjectManagerPosition() {
        LeadPosition leadPosition1 = new LeadPosition();
        leadPosition1.setProject(PROJECT);
        leadPosition1.setLead(LEAD);

        LeadPosition leadPosition2 = new LeadPosition();
        leadPosition2.setProject(PROJECT);
        leadPosition2.setLead(LEAD);

        assertTrue(leadPosition1.equals(leadPosition2));
    }

    @Test
    public void testNonEqualForNonPersistentProjectManagerPosition() {
        LeadPosition leadPosition1 = new LeadPosition();
        leadPosition1.setProject(PROJECT);
        leadPosition1.setLead(LEAD);

        LeadPosition leadPosition2 = new LeadPosition();
        leadPosition2.setProject(PROJECT2);
        leadPosition2.setLead(LEAD2);

        assertFalse(leadPosition1.equals(leadPosition2));
    }

    //ON HASH TESTS.

    @Test
    public void testEqualHashCodeForPersistentProjectManagerPosition() {
        LeadPosition leadPosition1 = new LeadPosition();
        leadPosition1.setId(ID);

        LeadPosition leadPosition2 = new LeadPosition();
        leadPosition2.setId(ID);

        assertTrue(leadPosition1.hashCode() == leadPosition2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentProjectManagerPosition() {
        LeadPosition leadPosition1 = new LeadPosition();
        leadPosition1.setId(ID);

        LeadPosition leadPosition2 = new LeadPosition();
        leadPosition2.setId(ID2);

        assertFalse(leadPosition1.hashCode() == leadPosition2.hashCode());
    }

    @Test
    public void testEqualHashCodeForNonPersistentProjectManagerPosition() {
        LeadPosition leadPosition1 = new LeadPosition();
        leadPosition1.setProject(PROJECT);
        leadPosition1.setLead(LEAD);

        LeadPosition leadPosition2 = new LeadPosition();
        leadPosition2.setProject(PROJECT);
        leadPosition2.setLead(LEAD);

        assertTrue(leadPosition1.hashCode() == leadPosition2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentProjectManagerPosition() {
        LeadPosition leadPosition1 = new LeadPosition();
        leadPosition1.setProject(PROJECT);
        leadPosition1.setLead(LEAD);

        LeadPosition leadPosition2 = new LeadPosition();
        leadPosition2.setProject(PROJECT2);
        leadPosition2.setLead(LEAD2);

        assertFalse(leadPosition1.hashCode() == leadPosition2.hashCode());
    }
}
