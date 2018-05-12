package cr.talent.model;

import javax.persistence.*;

/**
 * Class that represents a Password Reset Request within the Talent system. It contains the technical resource
 *, email to send the link to change the password, needed token , valid request boolean and
 * other information from {@link cr.talent.model.BasicEntity} class.
 *
 * @author María José Cubero
 */

@Entity
@Table(name = "password_reset_request")
public class PasswordResetRequest extends BasicEntity {

    /**
     * The technical resource that is requesting the password reset.
     */
    @OneToOne
    private TechnicalResource technicalResource;

    /**
     * The security token to put in the URL.
     */
    @Column(name = "token")
    private String token;

    /**
     * The email of the technical resource.
     */
    @Column(name = "email")
    private String email;

    /**
     * The flag to know if the request is still valid.
     */
    @Column(name= "is_valid")
    private boolean isValid;

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if (o instanceof PasswordResetRequest){
            PasswordResetRequest passwordResetRequest = (PasswordResetRequest) o;
            result = (this.technicalResource == null ? passwordResetRequest.getTechnicalResource() == null :
                    this.technicalResource.equals(passwordResetRequest.getTechnicalResource())
                    && this.token == null ? passwordResetRequest.getToken() == null :
                            this.token.equals(passwordResetRequest.getToken()));
        }
        return result;
    }

    @Override
    protected int onHashCode(int result) {
        final int prime = 23;
        result = prime * result + ((this.technicalResource == null)? 0 : this.technicalResource.hashCode());
        result = prime * result + ((this.token == null)? 0 : this.token.hashCode());
        return result;
    }

    public TechnicalResource getTechnicalResource() {
        return technicalResource;
    }

    public void setTechnicalResource(TechnicalResource technicalResource) {
        this.technicalResource = technicalResource;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        this.isValid = valid;
    }
}
