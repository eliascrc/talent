package cr.talent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

}
