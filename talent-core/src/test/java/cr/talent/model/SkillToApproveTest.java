package cr.talent.model;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;



/**
 * Class that allows to test the skills to approve methods to know all the different paths they could take.
 *  This class contains the test of the inherited methods ans attributes
 *  from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Otto Mena Kikut
 */
public class SkillToApproveTest {

    private static final TechnicalResource TECHNICAL_RESOURCE1 = mock(TechnicalResource.class);
    private static final TechnicalResource TECHNICAL_RESOURCE2 = mock(TechnicalResource.class);
    private static final Skill REQUESTED_SKILL1 = mock(Skill.class);
    private static final Skill REQUESTED_SKILL2 = mock(Skill.class);
    private static final String ID1 = "987";
    private static final String ID2 = "654";

    @Test
    public void coreTest() {
        SkillToApprove skillToApprove = new SkillToApprove();

        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;

        skillToApprove.setRequestingTechnicalResource(TECHNICAL_RESOURCE1);
        skillToApprove.setRequestedSkill(REQUESTED_SKILL1);
        skillToApprove.setEntityVersion(entityVersion);
        skillToApprove.setEntityCreationTimestamp(entityCreationTimestamp);
        skillToApprove.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        skillToApprove.setId(ID1);

        assertEquals(TECHNICAL_RESOURCE1, skillToApprove.getRequestingTechnicalResource());
        assertEquals(REQUESTED_SKILL1, skillToApprove.getRequestedSkill());
        assertEquals(entityCreationTimestamp, skillToApprove.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, skillToApprove.getLastUpdatedTimestamp());
        assertEquals(entityVersion, skillToApprove.getEntityVersion());
        assertEquals(ID1, skillToApprove.getId());
    }


    @Test
    public void testEqualForSameObject() {
        SkillToApprove skillToApprove1 = new SkillToApprove();

        assertTrue(skillToApprove1.equals(skillToApprove1));
    }

    @Test
    public void testEqualForDifferentClass() {
        SkillToApprove skillToApprove1 = new SkillToApprove();

        Image image= new Image();

        assertFalse(skillToApprove1.equals(image));
    }

    @Test
    public void testEqualForPersistentSkillToApprove() {
        SkillToApprove skillToApprove1 = new SkillToApprove();
        skillToApprove1.setId(ID1);

        SkillToApprove skillToApprove2 = new SkillToApprove();
        skillToApprove2.setId(ID1);

        assertTrue(skillToApprove1.equals(skillToApprove2));
    }

    @Test
    public void testNonEqualForPersistentSkillToApprove() {
        SkillToApprove skillToApprove1 = new SkillToApprove();
        skillToApprove1.setId(ID1);

        SkillToApprove skillToApprove2 = new SkillToApprove();
        skillToApprove2.setId(ID2);

        assertFalse(skillToApprove1.equals(skillToApprove2));
    }

    @Test
    public void testEqualForNonPersistentSkillToApprove() {
        SkillToApprove skillToApprove1 = new SkillToApprove();
        skillToApprove1.setRequestedSkill(REQUESTED_SKILL1);
        skillToApprove1.setRequestingTechnicalResource(TECHNICAL_RESOURCE1);

        SkillToApprove skillToApprove2 = new SkillToApprove();
        skillToApprove2.setRequestedSkill(REQUESTED_SKILL1);
        skillToApprove2.setRequestingTechnicalResource(TECHNICAL_RESOURCE1);

        assertTrue(skillToApprove1.equals(skillToApprove2));
    }

    @Test
    public void testEqualForNonPersistentSkillToApproveNullSkillNullResource(){
        SkillToApprove skillToApprove1 = new SkillToApprove();

        SkillToApprove skillToApprove2 = new SkillToApprove();

        assertTrue(skillToApprove1.equals(skillToApprove2));
    }

    @Test
    public void testEqualForNonPersistentSkillToApproveNullSkill() {
        SkillToApprove skillToApprove1 = new SkillToApprove();
        skillToApprove1.setRequestingTechnicalResource(TECHNICAL_RESOURCE1);

        SkillToApprove skillToApprove2 = new SkillToApprove();
        skillToApprove2.setRequestingTechnicalResource(TECHNICAL_RESOURCE1);

        assertTrue(skillToApprove1.equals(skillToApprove2));
    }

    @Test
    public void testEqualForNonPersistentSkillToApproveNullResource() {
        SkillToApprove skillToApprove1 = new SkillToApprove();
        skillToApprove1.setRequestedSkill(REQUESTED_SKILL1);

        SkillToApprove skillToApprove2 = new SkillToApprove();
        skillToApprove2.setRequestedSkill(REQUESTED_SKILL1);

        assertTrue(skillToApprove1.equals(skillToApprove2));
    }

    @Test
    public void testNonEqualForNonPersistentSkillToApprove() {
        SkillToApprove skillToApprove1 = new SkillToApprove();
        skillToApprove1.setRequestedSkill(REQUESTED_SKILL1);
        skillToApprove1.setRequestingTechnicalResource(TECHNICAL_RESOURCE1);

        SkillToApprove skillToApprove2 = new SkillToApprove();
        skillToApprove2.setRequestedSkill(REQUESTED_SKILL2);
        skillToApprove2.setRequestingTechnicalResource(TECHNICAL_RESOURCE2);

        assertFalse(skillToApprove1.equals(skillToApprove2));
    }

    @Test
    public void testNonEqualForNonPersistentSkillToApproveNullSkillNullResource() {
        SkillToApprove skillToApprove1 = new SkillToApprove();

        SkillToApprove skillToApprove2 = new SkillToApprove();
        skillToApprove2.setRequestedSkill(REQUESTED_SKILL2);
        skillToApprove2.setRequestingTechnicalResource(TECHNICAL_RESOURCE2);

        assertFalse(skillToApprove1.equals(skillToApprove2));
    }

    @Test
    public void testNonEqualForNonPersistentSkillToApproveNullSkill() {
        SkillToApprove skillToApprove1 = new SkillToApprove();
        skillToApprove1.setRequestingTechnicalResource(TECHNICAL_RESOURCE1);

        SkillToApprove skillToApprove2 = new SkillToApprove();
        skillToApprove2.setRequestedSkill(REQUESTED_SKILL2);
        skillToApprove2.setRequestingTechnicalResource(TECHNICAL_RESOURCE2);

        assertFalse(skillToApprove1.equals(skillToApprove2));
    }

    @Test
    public void testNonEqualForNonPersistentSkillToApproveNullResource() {
        SkillToApprove skillToApprove1 = new SkillToApprove();
        skillToApprove1.setRequestedSkill(REQUESTED_SKILL1);

        SkillToApprove skillToApprove2 = new SkillToApprove();
        skillToApprove2.setRequestedSkill(REQUESTED_SKILL1);
        skillToApprove2.setRequestingTechnicalResource(TECHNICAL_RESOURCE2);

        assertFalse(skillToApprove1.equals(skillToApprove2));
    }

    @Test
    public void testEqualHashCodeForPersistentSkillToApprove() {
        SkillToApprove skillToApprove1 = new SkillToApprove();
        skillToApprove1.setId(ID1);

        SkillToApprove skillToApprove2 = new SkillToApprove();
        skillToApprove2.setId(ID1);

        assertTrue(skillToApprove1.hashCode() == skillToApprove2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentSkillToApprove() {
        SkillToApprove skillToApprove1 = new SkillToApprove();
        skillToApprove1.setId(ID1);

        SkillToApprove skillToApprove2 = new SkillToApprove();
        skillToApprove2.setId(ID2);

        assertFalse(skillToApprove1.hashCode() == skillToApprove2.hashCode());
    }



    @Test
    public void testEqualHashCodeForNonPersistentSkillToApprove() {

        SkillToApprove skillToApprove1 = new SkillToApprove();
        skillToApprove1.setRequestedSkill(REQUESTED_SKILL1);
        skillToApprove1.setRequestingTechnicalResource(TECHNICAL_RESOURCE1);

        SkillToApprove skillToApprove2 = new SkillToApprove();
        skillToApprove2.setRequestedSkill(REQUESTED_SKILL1);
        skillToApprove2.setRequestingTechnicalResource(TECHNICAL_RESOURCE1);

        assertTrue(skillToApprove1.hashCode() == skillToApprove2.hashCode());

    }

    @Test
    public void testNonEqualHashCodeForNonPersistentSkillToApprove() {
        SkillToApprove skillToApprove1 = new SkillToApprove();
        skillToApprove1.setRequestedSkill(REQUESTED_SKILL1);
        skillToApprove1.setRequestingTechnicalResource(TECHNICAL_RESOURCE1);

        SkillToApprove skillToApprove2 = new SkillToApprove();
        skillToApprove2.setRequestedSkill(REQUESTED_SKILL2);
        skillToApprove2.setRequestingTechnicalResource(TECHNICAL_RESOURCE2);

        assertFalse(skillToApprove1.hashCode() == skillToApprove2.hashCode());
    }
}

