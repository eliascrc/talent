package cr.talent.model;

public class TwoStepVerificationMessage extends BasicEntity {

    private String message;

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }
}
