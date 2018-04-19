package cr.talent.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Class that represents a two step verification message within the Talent system.
 * It contains the message.
 *
 * @author Elías Calderón
 */
@Entity
@Table(name = "two_step_verification_message")
public class TwoStepVerificationMessage extends BasicEntity{

    /**
     * The two step verification list
     */
    @OneToMany (cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "message")
    private Set<TwoStepVerification> twoStepVerification;

    /**
     * The message that all the two step verifications have.
     */
    @Column(name = "message", nullable = false)
    private String message;

    public TwoStepVerificationMessage(){}

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if ( o instanceof TwoStepVerificationMessage){
            TwoStepVerificationMessage TwoStepVerificationMessage = (TwoStepVerificationMessage) o;
            result = (this.message == null ? TwoStepVerificationMessage.getMessage() == null :
                    this.message.equals(TwoStepVerificationMessage.getMessage()));
        }
        return result;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<TwoStepVerification> getTwoStepVerification() {
        return twoStepVerification;
    }

    public void setTwoStepVerification(Set<TwoStepVerification> twoStepVerification) {
        this.twoStepVerification = twoStepVerification;
    }
}
