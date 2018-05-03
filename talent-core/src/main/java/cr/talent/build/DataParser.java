package cr.talent.build;

import cr.talent.model.*;
import nu.xom.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class extends the XmlParser class, it parses the xml file while creating the objects that corr and setting their attributes.
 *
 * @author Daniel Montes de Oca & Otto Mena Kikut
 */
class DataParser extends XmlParser {

    private List<Organization> organizations;
    private List<TechnicalResource> technicalResources;
    private List<TermsOfService> termsOfServiceVersions;

    /**
     * The class constructor. It receives the filepath to the xml file.
     * It creates the ArrayLists that will store each object corresponding to a row of the database.
     *
     *
     * @param filepath
     */
    DataParser(String filepath) {
        super(filepath);
        this.organizations = new ArrayList<Organization>();
        this.technicalResources = new ArrayList<TechnicalResource>();
        this.termsOfServiceVersions = new ArrayList<>();
    }

    void parseData() {
        this.fillOrganizations();
        this.fillTechnicalResources();
        this.fillTermsOfServiceVersions();
    }


    /**
     * Fill the organizations List.
     */
    private void fillOrganizations() {
        Elements organizationElements = getElementOfType("organizations").get(0).getChildElements();
        for (int i = 0; i < organizationElements.size(); i++){
            Organization organization = getOrganization(organizationElements.get(i));
            this.organizations.add(organization);
        }
    }

    /**
     * This method creates an Organization object and sets its attributes according to the text in the xml
     *
     * @param organizationElement the node of the xml file corresponding to the specific organization.
     * @return
     */
    private Organization getOrganization(Element organizationElement) {
        Organization organization = new Organization();
        organization.setUniqueIdentifier(super.getAttributeValue(organizationElement, "uniqueIdentifier"));
        organization.setName(super.getAttributeValue(organizationElement, "name"));
        organization.setTwoStepVerification(super.getBooleanValue(organizationElement, "twoStepVerification"));
        organization.setState(OrganizationState.valueOf(super.getAttributeValue(organizationElement, "state")));
        organization.setUserAuthenticationMethod(UserAuthenticationMethod.valueOf(super.getAttributeValue(organizationElement, "userAuthenticationMethod")));
        organization.setDomain(super.getAttributeValue(organizationElement, "domain"));

        return organization;
    }

    /**
     * Fill the technicalResources List.
     */
    private void fillTechnicalResources () {
        Elements technicalResourceElements = getElementOfType("technicalResources").get(0).getChildElements();
        for (int i = 0; i < technicalResourceElements.size(); i++) {
            TechnicalResource technicalResource = getTechnicalResource(technicalResourceElements.get(i));
            this.technicalResources.add(technicalResource);
        }
    }

    /**This method creates a TechnicalResource and sets its attributes according to the text in the xml file.
     *
     * @param technicalResourceElement the node of the xml file corresponding to the specific technical resource.
     * @return
     */
    private TechnicalResource getTechnicalResource(Element technicalResourceElement) {
        TechnicalResource technicalResource = new TechnicalResource();
        technicalResource.setUsername(super.getAttributeValue(technicalResourceElement, "username"));
        technicalResource.setFirstName(super.getAttributeValue(technicalResourceElement, "firstName"));
        technicalResource.setLastName(super.getAttributeValue(technicalResourceElement, "lastName"));
        technicalResource.setPassword(super.getAttributeValue(technicalResourceElement, "password"));
        technicalResource.setEnabled(super.getBooleanValue(technicalResourceElement, "enabled"));
        technicalResource.setPasswordNeedsChange(super.getBooleanValue(technicalResourceElement, "passwordNeedsChange"));
        technicalResource.setStatus(User.Status.valueOf(super.getAttributeValue(technicalResourceElement, "status")));
        technicalResource.setAdministrator(super.getBooleanValue(technicalResourceElement, "isAdministrator"));
        technicalResource.setLastLevelAssessment(super.getDateValue(technicalResourceElement, "lastLevelAssessment"));
        technicalResource.setLastPerformanceReview(super.getDateValue(technicalResourceElement, "lastPerformanceReview"));
        this.linkOrganization(technicalResource, super.getAttributeValue(technicalResourceElement, "organization"));
        technicalResource.setTimeZone(super.getAttributeValue(technicalResourceElement, "timeZone"));
        technicalResource.setLevelAssessmentTimeGap(super.getIntValue(technicalResourceElement, "levelAssessmentTimeGap"));

        return technicalResource;
    }

    /**This method sets the organization attribute to the technical resource object.
     *
     * @param technicalResource the technical resource object which must be linked to respective organization
     * @param orgUniqueIdentifier the unique identifier of the organization to which the technical resource will be linked.
     */
    private void linkOrganization(TechnicalResource technicalResource, String orgUniqueIdentifier) {
        Organization organization = null;

        for (Organization organizationIterator : this.organizations) {
            if (organizationIterator.getUniqueIdentifier().equals(orgUniqueIdentifier)) {
                organization = organizationIterator;
                break;
            }
        }
        assert organization != null;

        technicalResource.setOrganization(organization);
        organization.setTotalUsers(organization.getTotalUsers() + 1);
    }

    /**
     * Fills the terms of service versions list.
     */
    private void fillTermsOfServiceVersions() {
        Elements termsOfServiceVersionsElements = getElementOfType("termsOfServiceVersions").get(0).getChildElements();
        for (int i = 0; i < termsOfServiceVersionsElements.size(); i++){
            TermsOfService termsOfService = getTermsOfService(termsOfServiceVersionsElements.get(i));
            this.termsOfServiceVersions.add(termsOfService);
        }
    }

    /**
     * Creates a TermsOfService object and sets its attributes according to the data specified in the DummyData.xml.
     * To set the terms of service HTML content, it obtains the file which stores the content from the xml and then
     * reads it.
     *
     * @param termsOfServiceElement the node of the xml file corresponding to the specific terms of service version
     * @return the TermsOfService created object
     */
    private TermsOfService getTermsOfService(Element termsOfServiceElement) {
        TermsOfService termsOfService = new TermsOfService();
        if(super.getDateValue(termsOfServiceElement, "startDate") != null)
        	termsOfService.setStartDate(super.getDateValue(termsOfServiceElement, "startDate"));
        if(super.getDateValue(termsOfServiceElement, "endDate") != null)
        	termsOfService.setEndDate(super.getDateValue(termsOfServiceElement, "endDate"));
        termsOfService.setActive(super.getBooleanValue(termsOfServiceElement, "isActive"));
        String termsOfServiceContent = "";
        try {
            termsOfServiceContent = FileUtils.readFileToString(new File(super.getAttributeValue(termsOfServiceElement, "contentFile")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        termsOfService.setToSContent(termsOfServiceContent);
        return termsOfService;
    }

    List<Organization> getOrganizations() {
        return this.organizations;
    }

    List<TechnicalResource> getTechnicalResources() {
        return this.technicalResources;
    }

    public List<TermsOfService> getTermsOfServiceVersions() {
        return termsOfServiceVersions;
    }
}
