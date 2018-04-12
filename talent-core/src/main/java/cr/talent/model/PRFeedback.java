package cr.talent.model;

public class PRFeedback extends BasicEntity {

    private TechnicalResource author;

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }

}
