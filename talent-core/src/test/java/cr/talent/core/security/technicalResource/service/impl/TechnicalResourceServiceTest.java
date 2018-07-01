package cr.talent.core.security.technicalResource.service.impl;

import cr.talent.core.careerPath.service.CareerPathService;
import cr.talent.core.careerPath.service.impl.CareerPathServiceImpl;
import cr.talent.core.security.technicalResource.dao.TechnicalResourceDao;
import cr.talent.core.security.technicalResource.dao.impl.HibernateTechnicalResourceDao;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.core.technicalPosition.service.TechnicalPositionService;
import cr.talent.core.technicalPosition.service.impl.TechnicalPositionServiceImpl;
import cr.talent.model.*;
import cr.talent.support.exceptions.AlreadyAssignedTechnicalPositionException;
import cr.talent.support.exceptions.NonExistentCapabilityException;
import cr.talent.support.exceptions.NonExistentCapabilityLevelException;
import cr.talent.support.exceptions.UserDoesNotHaveRequiredSkillsException;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static org.mockito.Mockito.*;

/**
 * Class that allows to test the TechnicalResourceServiceImpl methods to know all the different paths they could take.
 *
 * @author Fabián Roberto Leandro
 */
public class TechnicalResourceServiceTest {
    private static final String USERNAME = "username";
    private static final String CAPABILITY_NAME = "capabilityName";
    private static final String CAPABILITY_LEVEL_NAME = "capabilityLevelName";
    private static final String REQUIRED_SKILL_NAME = "REQUIRED_SKILL_NAME";
    private static final Date START_DATE = new Date();


    @Test
    public void assignTechnicalPositionTest() {
        TechnicalPositionService technicalPositionService = mock(TechnicalPositionServiceImpl.class);
        CareerPathService careerPathService = mock(CareerPathServiceImpl.class);
        TechnicalResourceDao technicalResourceDao = mock(HibernateTechnicalResourceDao.class);

        TechnicalResourceService technicalResourceService = new TechnicalResourceServiceImpl();

        ReflectionTestUtils.setField(technicalResourceService, "crudDao", technicalResourceDao);
        ReflectionTestUtils.setField(technicalResourceService, "technicalPositionService", technicalPositionService);
        ReflectionTestUtils.setField(technicalResourceService, "careerPathService", careerPathService);

        CapabilityLevel capabilityLevel = new CapabilityLevel();
        capabilityLevel.setName(CAPABILITY_LEVEL_NAME);

        Capability capability = new Capability();
        capability.setName(CAPABILITY_NAME);
        capability.setLevelHierarchy(new HashSet<>(Arrays.asList(capabilityLevel)));

        Organization organization = new Organization();
        organization.setCapabilities(new HashSet<>(Arrays.asList(capability)));

        TechnicalResource technicalResource = new TechnicalResource();
        technicalResource.setUsername(USERNAME);

        technicalResourceService.assignTechnicalPositionToTechnicalResource(CAPABILITY_NAME,CAPABILITY_LEVEL_NAME,
                organization,technicalResource,START_DATE);
    }

    @Test
    public void assignTechnicalPositionNonExistentCapabilityTest() {
        TechnicalPositionService technicalPositionService = mock(TechnicalPositionServiceImpl.class);
        CareerPathService careerPathService = mock(CareerPathServiceImpl.class);
        TechnicalResourceDao technicalResourceDao = mock(HibernateTechnicalResourceDao.class);

        TechnicalResourceService technicalResourceService = new TechnicalResourceServiceImpl();

        ReflectionTestUtils.setField(technicalResourceService, "crudDao", technicalResourceDao);
        ReflectionTestUtils.setField(technicalResourceService, "technicalPositionService", technicalPositionService);
        ReflectionTestUtils.setField(technicalResourceService, "careerPathService", careerPathService);

        Organization organization = new Organization();

        TechnicalResource technicalResource = new TechnicalResource();
        technicalResource.setUsername(USERNAME);

        try {
            technicalResourceService.assignTechnicalPositionToTechnicalResource(CAPABILITY_NAME,CAPABILITY_LEVEL_NAME,
                organization,technicalResource,START_DATE);
        } catch (NonExistentCapabilityException e) {
            // This exception should be thrown
        }
    }

    @Test
    public void assignTechnicalPositionNonExistentCapabilityLevelTest() {
        TechnicalPositionService technicalPositionService = mock(TechnicalPositionServiceImpl.class);
        CareerPathService careerPathService = mock(CareerPathServiceImpl.class);
        TechnicalResourceDao technicalResourceDao = mock(HibernateTechnicalResourceDao.class);

        TechnicalResourceService technicalResourceService = new TechnicalResourceServiceImpl();

        ReflectionTestUtils.setField(technicalResourceService, "crudDao", technicalResourceDao);
        ReflectionTestUtils.setField(technicalResourceService, "technicalPositionService", technicalPositionService);
        ReflectionTestUtils.setField(technicalResourceService, "careerPathService", careerPathService);

        Capability capability = new Capability();
        capability.setName(CAPABILITY_NAME);

        Organization organization = new Organization();
        organization.setCapabilities(new HashSet<>(Arrays.asList(capability)));

        TechnicalResource technicalResource = new TechnicalResource();
        technicalResource.setUsername(USERNAME);

        try {
            technicalResourceService.assignTechnicalPositionToTechnicalResource(CAPABILITY_NAME,CAPABILITY_LEVEL_NAME,
                organization,technicalResource,START_DATE);
        } catch (NonExistentCapabilityLevelException e) {
            // This exception should be thrown
        }
    }

    @Test
    public void assignTechnicalPositionTestTechnicalPositionAlreadyAssigned() {
        TechnicalPositionService technicalPositionService = mock(TechnicalPositionServiceImpl.class);
        CareerPathService careerPathService = mock(CareerPathServiceImpl.class);
        TechnicalResourceDao technicalResourceDao = mock(HibernateTechnicalResourceDao.class);

        TechnicalResourceService technicalResourceService = new TechnicalResourceServiceImpl();

        ReflectionTestUtils.setField(technicalResourceService, "crudDao", technicalResourceDao);
        ReflectionTestUtils.setField(technicalResourceService, "technicalPositionService", technicalPositionService);
        ReflectionTestUtils.setField(technicalResourceService, "careerPathService", careerPathService);

        CapabilityLevel capabilityLevel = new CapabilityLevel();
        capabilityLevel.setName(CAPABILITY_LEVEL_NAME);

        Capability capability = new Capability();
        capability.setName(CAPABILITY_NAME);
        capability.setLevelHierarchy(new HashSet<>(Arrays.asList(capabilityLevel)));

        Organization organization = new Organization();
        organization.setCapabilities(new HashSet<>(Arrays.asList(capability)));

        TechnicalResource technicalResource = new TechnicalResource();
        technicalResource.setUsername(USERNAME);

        technicalResourceService.assignTechnicalPositionToTechnicalResource(CAPABILITY_NAME,CAPABILITY_LEVEL_NAME,
                organization,technicalResource,START_DATE);

        try {
            technicalResourceService.assignTechnicalPositionToTechnicalResource(CAPABILITY_NAME,CAPABILITY_LEVEL_NAME,
                organization,technicalResource,START_DATE);
        } catch (AlreadyAssignedTechnicalPositionException e) {
            // This exception should be thrown
        }
    }

    @Test
    public void assignTechnicalPositionUserDoesNotHaveRequiredSkillTest() {
        TechnicalPositionService technicalPositionService = mock(TechnicalPositionServiceImpl.class);
        CareerPathService careerPathService = mock(CareerPathServiceImpl.class);
        TechnicalResourceDao technicalResourceDao = mock(HibernateTechnicalResourceDao.class);

        TechnicalResourceService technicalResourceService = new TechnicalResourceServiceImpl();

        ReflectionTestUtils.setField(technicalResourceService, "crudDao", technicalResourceDao);
        ReflectionTestUtils.setField(technicalResourceService, "technicalPositionService", technicalPositionService);
        ReflectionTestUtils.setField(technicalResourceService, "careerPathService", careerPathService);

        CapabilityLevel capabilityLevel = new CapabilityLevel();
        OrganizationSkill requiredSkill = new OrganizationSkill();
        requiredSkill.setName(REQUIRED_SKILL_NAME);
        capabilityLevel.setRequiredSkills(new HashSet<>(Arrays.asList(requiredSkill)));
        capabilityLevel.setName(CAPABILITY_LEVEL_NAME);

        Capability capability = new Capability();
        capability.setName(CAPABILITY_NAME);
        capability.setLevelHierarchy(new HashSet<>(Arrays.asList(capabilityLevel)));

        Organization organization = new Organization();
        organization.setCapabilities(new HashSet<>(Arrays.asList(capability)));

        TechnicalResource technicalResource = new TechnicalResource();
        technicalResource.setUsername(USERNAME);

        try {
            technicalResourceService.assignTechnicalPositionToTechnicalResource(CAPABILITY_NAME,CAPABILITY_LEVEL_NAME,
                organization,technicalResource,START_DATE);
        } catch (UserDoesNotHaveRequiredSkillsException e) {
            // This exception should be thrown
        }
    }
}
