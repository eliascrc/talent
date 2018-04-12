package cr.talent.model;

public class LATest extends BasicEntity {

    private String name;
    private LATestType type;
    private Reviewer reviewer;
    private LAState state;
    private LevelAssessment assignatedQuery;

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }
}
