package cr.talent.model;

import org.junit.Test;

import java.util.Date;
import java.util.HashSet;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Class that allows to test the organization methods, to know all the different paths they could take.
 * This class contains the test of the inherited methods and attributes
 * from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Maria Jose Cubero
 */
public class OrganizationTest {

    private static final String ID = "1234";
    private static final String ID2 = "12345";
    private static final String UNIQUE_IDENTIFIER = "4321";
    private static final String UNIQUE_IDENTIFIER2 = "54321";

    @Test
    public void CoreTest(){

        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;
        String name =  "talent";
        Boolean twoStepVerification = true;
        int totalUsers = 5;
        String domain = "talent.cr";
        Image logo = mock(Image.class);

        //Verify the Constructor
        Organization organization = new Organization();

        //Verify the sets
        organization.setId(ID);
        organization.setUniqueIdentifier(UNIQUE_IDENTIFIER);
        organization.setEntityCreationTimestamp(entityCreationTimestamp);
        organization.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        organization.setEntityVersion(entityVersion);
        organization.setName(name);
        organization.setTwoStepVerification(twoStepVerification);
        organization.setTotalUsers(totalUsers);
        organization.setDomain(domain);
        organization.setLogo(logo);
        organization.setState(OrganizationState.ENABLED);
        organization.setUserAuthenticationMethod(UserAuthenticationMethod.BASIC);
        organization.setInvitationsList(new HashSet());
        organization.setResources(new HashSet<>());
        organization.setCapabilities(new HashSet<>());
        organization.setSkillCategories(new HashSet<>());
        organization.setProjects(new HashSet<>());
        organization.setHumanResourceManagers(new HashSet<>());
        organization.setTechnicalManagers(new HashSet<>());

        //Verify the gets
        assertEquals(ID, organization.getId());
        assertEquals(UNIQUE_IDENTIFIER, organization.getUniqueIdentifier());
        assertEquals(entityCreationTimestamp, organization.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp,  organization.getLastUpdatedTimestamp());
        assertEquals(entityVersion, organization.getEntityVersion());
        assertEquals(name, organization.getName());
        assertEquals(twoStepVerification, organization.getTwoStepVerification());
        assertEquals(totalUsers, organization.getTotalUsers());
        assertEquals(domain, organization.getDomain());
        assertEquals(logo, organization.getLogo());
        assertEquals(OrganizationState.ENABLED, organization.getState());
        assertEquals(UserAuthenticationMethod.BASIC, organization.getUserAuthenticationMethod());
        assertNotNull(organization.getInvitationsList());
        assertNotNull(organization.getResources());
        assertNotNull(organization.getCapabilities());
        assertNotNull(organization.getSkillCategories());
        assertNotNull(organization.getProjects());
        assertNotNull(organization.getHumanResourceManagers());
        assertNotNull(organization.getTechnicalManagers());

    }

    //ON EQUALS TESTS.

    @Test
    public void testEqualForSameObject() {
        Project project1 = new Project();

        assertTrue(project1.equals(project1));
    }

    @Test
    public void testEqualForDifferentClass() {
        Project project1 = new Project();

        assertFalse(project1.equals(new Object()));
    }


}
