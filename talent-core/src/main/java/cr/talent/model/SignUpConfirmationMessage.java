package cr.talent.model;

import javax.persistence.*;

/**
 * Class that represents a SignUpConfirmationMessage within the Talent system.
 * It contains the technical resource doing the sign up and the code to confirm that they are in fact doing the process.
 * It also contains the information inherited from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Daniel Montes de Oca
 */
@Entity
@Table(name = "sign_up_confirmation_message")
public class SignUpConfirmationMessage extends BasicEntity {

    /**
     * The technical resource doing the sign up.
     */
    @OneToOne
    @JoinColumn(name = "technical_resource_id", unique = true)
    private TechnicalResource technicalResource;

    /**
     * The code that the user will have to match to continue the sign up process
     */
    @Column(name = "confirmation_code", nullable = false)
    private String confirmationCode;

    public SignUpConfirmationMessage() {
    }

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if (o instanceof SignUpConfirmationMessage) {
            SignUpConfirmationMessage signUpConfirmationMessage = (SignUpConfirmationMessage) o;
            result = (this.technicalResource == null ? signUpConfirmationMessage.getTechnicalResource() == null :
                    this.technicalResource.equals(signUpConfirmationMessage.getTechnicalResource()));
        }
        return result;
    }

    @Override
    protected int onHashCode(int result) {
        final int prime = 23;
        result = prime * result + (this.technicalResource == null ? 0 : this.technicalResource.hashCode());
        return result;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public TechnicalResource getTechnicalResource() {
        return technicalResource;
    }

    public void setTechnicalResource(TechnicalResource technicalResource) {
        this.technicalResource = technicalResource;
    }

}
