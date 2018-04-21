package cr.talent.build;

import cr.talent.model.*;
import nu.xom.*;

import java.util.ArrayList;
import java.util.List;

class DataParser extends XmlParser {

    private List<Organization> organizations;
    private List<TechnicalResource> technicalResources;

    DataParser(String filepath) {
        super(filepath);
        this.organizations = new ArrayList<Organization>();
        this.technicalResources = new ArrayList<TechnicalResource>();
    }

    void parseData() {
        this.fillOrganizations();
        this.fillTechnicalResources();
    }


    private void fillOrganizations() {
        Elements organizationElements = getElementOfType("organizations").get(0).getChildElements();
        for (int i = 0; i < organizationElements.size(); i++){
            Organization organization = getOrganization(organizationElements.get(i));
            this.organizations.add(organization);
        }
    }

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

    private void fillTechnicalResources () {
        Elements technicalResourceElements = getElementOfType("technicalResources").get(0).getChildElements();
        for (int i = 0; i < technicalResourceElements.size(); i++) {
            TechnicalResource technicalResource = getTechnicalResource(technicalResourceElements.get(i));
            this.technicalResources.add(technicalResource);
        }
    }

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

    List<Organization> getOrganizations() {
        return this.organizations;
    }

    List<TechnicalResource> getTechnicalResources() {
        return this.technicalResources;
    }

}
