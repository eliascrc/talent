package cr.talent.model;

/**
 * Class that represents a two step verification message within the Talent system.
 * It contains the message and the information inherited from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Elías Calderón
 */
public class TwoStepVerificationMessage extends BasicEntity {

    /**
     * The message that all the two step verifications have.
     */
    private String message;

    public TwoStepVerificationMessage(){}

    @Override
    protected boolean onEquals(Object o) {
        return false;
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
