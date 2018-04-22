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
        assertNotNull(skillToApprove.getRequestedSkill());
        assertNotNull(skillToApprove.getRequestingTechnicalResource());
    }


    @Test
    public void testEqualForSameObject() {
        SkillToApprove skillToApprove1 = new SkillToApprove();

        assertTrue(skillToApprove1.equals(skillToApprove1));
    }

    @Test
    public void testEqualForDifferentClass() {
        SkillToApprove skillToApprove1 = new SkillToApprove();

        assertFalse(skillToApprove1.equals(new Object()));
    }

    @Test
    public void testEqualForPersistentSkillsToApprove() {
        SkillToApprove skillToApprove1 = new SkillToApprove();
        skillToApprove1.setId(ID1);

        SkillToApprove skillToApprove2 = new SkillToApprove();
        skillToApprove2.setId(ID1);

        assertTrue(skillToApprove1.equals(skillToApprove2));
    }

    @Test
    public void testNonEqualForPersistentSkillsToApprove() {
        SkillToApprove skillToApprove1 = new SkillToApprove();
        skillToApprove1.setId(ID1);

        SkillToApprove skillToApprove2 = new SkillToApprove();
        skillToApprove2.setId(ID2);

        assertFalse(skillToApprove1.equals(skillToApprove2));
    }

    @Test
    public void testEqualForNonPersistentSkillsToApprove() {
        SkillToApprove skillToApprove1 = new SkillToApprove();
        skillToApprove1.setRequestedSkill(REQUESTED_SKILL1);
        skillToApprove1.setRequestingTechnicalResource(TECHNICAL_RESOURCE1);

        SkillToApprove skillToApprove2 = new SkillToApprove();
        skillToApprove2.setRequestedSkill(REQUESTED_SKILL1);
        skillToApprove2.setRequestingTechnicalResource(TECHNICAL_RESOURCE1);

        assertTrue(skillToApprove1.equals(skillToApprove2));
    }

    @Test
    public void testNonEqualForNonPersistentSkillsToApprove() {
        SkillToApprove skillToApprove1 = new SkillToApprove();
        skillToApprove1.setRequestedSkill(REQUESTED_SKILL1);
        skillToApprove1.setRequestingTechnicalResource(TECHNICAL_RESOURCE1);

        SkillToApprove skillToApprove2 = new SkillToApprove();
        skillToApprove2.setRequestedSkill(REQUESTED_SKILL2);
        skillToApprove2.setRequestingTechnicalResource(TECHNICAL_RESOURCE2);

        assertFalse(skillToApprove1.equals(skillToApprove2));
    }



    @Test
    public void testEqualHashCodeForPersistentSkillsToApprove() {
        SkillToApprove skillToApprove1 = new SkillToApprove();
        skillToApprove1.setId(ID1);

        SkillToApprove skillToApprove2 = new SkillToApprove();
        skillToApprove2.setId(ID1);

        assertTrue(skillToApprove1.hashCode() == skillToApprove2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentSkillsToApprove() {
        SkillToApprove skillToApprove1 = new SkillToApprove();
        skillToApprove1.setId(ID1);

        SkillToApprove skillToApprove2 = new SkillToApprove();
        skillToApprove2.setId(ID2);

        assertFalse(skillToApprove1.hashCode() == skillToApprove2.hashCode());
    }



    @Test
    public void testEqualHashCodeForNonPersistentSkillsToApprove() {

        SkillToApprove skillToApprove1 = new SkillToApprove();
        skillToApprove1.setRequestedSkill(REQUESTED_SKILL1);
        skillToApprove1.setRequestingTechnicalResource(TECHNICAL_RESOURCE1);

        SkillToApprove skillToApprove2 = new SkillToApprove();
        skillToApprove2.setRequestedSkill(REQUESTED_SKILL1);
        skillToApprove2.setRequestingTechnicalResource(TECHNICAL_RESOURCE1);

        assertTrue(skillToApprove1.hashCode() == skillToApprove2.hashCode());

    }

    @Test
    public void testNonEqualHashCodeForNonPersistentSkillsToApprove() {
        SkillToApprove skillToApprove1 = new SkillToApprove();
        skillToApprove1.setRequestedSkill(REQUESTED_SKILL1);
        skillToApprove1.setRequestingTechnicalResource(TECHNICAL_RESOURCE1);

        SkillToApprove skillToApprove2 = new SkillToApprove();
        skillToApprove2.setRequestedSkill(REQUESTED_SKILL2);
        skillToApprove2.setRequestingTechnicalResource(TECHNICAL_RESOURCE2);

        assertFalse(skillToApprove1.hashCode() == skillToApprove2.hashCode());
    }
}

