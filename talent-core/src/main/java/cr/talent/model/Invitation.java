package cr.talent.model;

import javax.persistence.*;

/**
 * Class that represents an invitation to join the Talent system.
 * It contains the link of the image and the information inherited from
 * {@link cr.talent.model.BasicEntity} class.
 *
 * @author Elías Calderón, Josue Cubero
 */
@Entity
@Table(name = "invitation")
public class Invitation extends BasicEntity {

    /**
     * The email of the person that is going to receive the invitation.
     */
    @Column (name = "email", nullable = false)
    private String email;

    /**
     * The first name of the person that is going to receive the invitation.
     */
    @Column (name = "invited_resource_first_name", nullable = false)
    private String invitedResourceFirstName;

    /**
     * The last name of the person that is going to receive the invitation.
     */
    @Column (name = "invited_resource_last_name", nullable = false)
    private String invitedResourceLastName;

    /**
     * The first name of the person that is making the invitation.
     */
    @Column (name = "inviter_resource_first_name", nullable = false)
    private String inviterResourceFirstName;

    /**
     * The last name of the person that is making the invitation.
     */
    @Column (name = "inviter_resource_last_name", nullable = false)
    private String inviterResourceLastName;

    /**
     * The security token to put in the URL.
     */
    @Column(name = "token")
    private String token;

    /**
     * Indicates if the invitation is valid
     */
    @Column(name = "is_valid")
    private boolean isValid;

    /**
     * The organization where the invitation came from.
     */
    @ManyToOne
    @JoinColumn (name = "organization_id", nullable = false)
    private Organization organization;

    public Invitation (){}

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if ( o instanceof Invitation){
            Invitation invitation = (Invitation) o;
            result = (this.token == null ? invitation.getToken() == null : this.token.equals(invitation.getToken()));
        }
        return result;
    }

    @Override
    protected int onHashCode(int result) {
        final int prime = 23;
        result = prime * result + (this.token == null ? 0 : this.token.hashCode());
        return result;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInvitedResourceFirstName() {
        return invitedResourceFirstName;
    }

    public void setInvitedResourceFirstName(String invitedResourceFirstName) {
        this.invitedResourceFirstName = invitedResourceFirstName;
    }

    public String getInvitedResourceLastName() {
        return invitedResourceLastName;
    }

    public void setInvitedResourceLastName(String invitedResourceLastName) {
        this.invitedResourceLastName = invitedResourceLastName;
    }

    public String getInviterResourceFirstName() {
        return inviterResourceFirstName;
    }

    public void setInviterResourceFirstName(String inviterResourceFirstName) {
        this.inviterResourceFirstName = inviterResourceFirstName;
    }

    public String getInviterResourceLastName() {
        return inviterResourceLastName;
    }

    public void setInviterResourceLastName(String inviterResourceLastName) {
        this.inviterResourceLastName = inviterResourceLastName;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public OrganizationLogo getLogo(){
        return this.organization.getLogo();
    }
}