package cr.talent.model;

/**
 * Class that represents a two step verification within the Talent system. It contains the technical resource
 * organization, message, verification code and the information inherited from {@link cr.talent.model.BasicEntity} class.
 *
 * @author María José Cubero
 */
public class TwoStepVerification extends BasicEntity{

    /**
     * The resource that will have the two step verification.
     */
    private TechnicalResource resource;

    /**
     * The message to send in the two step verification.
     */
    private TwoStepVerificationMessage message;

    /**
     * The verification code of the two step verification.
     */
    private String verificationCode;

    public TwoStepVerification(){}

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }

    public TechnicalResource getResource() {
        return resource;
    }

    public void setResource(TechnicalResource resource) {
        this.resource = resource;
    }

    public TwoStepVerificationMessage getMessage() {
        return message;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
