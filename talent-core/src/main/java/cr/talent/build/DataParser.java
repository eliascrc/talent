package cr.talent.build;

import cr.talent.model.Organization;
import cr.talent.model.OrganizationState;
import cr.talent.model.UserAuthenticationMethod;
import nu.xom.*;

import java.util.ArrayList;
import java.util.List;

public class DataParser extends XmlParser {

    private List<Organization> organizations;

    DataParser(String filepath) {
        super(filepath);
        this.organizations = new ArrayList<Organization>();
    }


    private void fillOrganizations() {
        Elements organizations = getElementOfType("organizations");
        for (int i = 0; i < organizations.size(); i++){
            Organization o = getOrganization(organizations.get(i));
            this.organizations.add(o);
        }
    }

    private Organization getOrganization(Element organizationElement) {
        Organization organization = new Organization();
        organization.setUniqueIdentifier(this.getAttributeValue(organizationElement, "uniqueIdentifier"));
        organization.setName(this.getAttributeValue(organizationElement, "name"));
        organization.setTwoStepVerification(this.getBooleanValue(organizationElement, "twoStepVerification"));
        organization.setState(OrganizationState.valueOf(this.getAttributeValue(organizationElement, "state")));
        organization.setUserAuthenticationMethod(UserAuthenticationMethod.valueOf(this.getAttributeValue(organizationElement, "userAuthenticationMethod")));
        organization.setLogo(this.getImage(organizationElement, "logo"));
        organization.setDomain(this.getAttributeValue(organizationElement, "domain"));

        return organization;
    }


}
