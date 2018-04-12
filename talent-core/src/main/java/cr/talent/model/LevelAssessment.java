package cr.talent.model;

import java.util.List;

public class LevelAssessment  extends BasicEntity{

    private List<LATest> tests;
    private LAState state;
    private TechnicalResource technicalResource;
    private TechnicalManager technicalManager;

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }
}
