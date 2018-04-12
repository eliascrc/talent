package cr.talent.model;


public class TwoStepVerification extends BasicEntity{

    private TechnicalResource resource;
    private TwoStepVerificationMessage message;
    private String verificationCode;

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }
}
