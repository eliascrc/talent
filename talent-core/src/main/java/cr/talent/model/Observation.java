package cr.talent.model;

public class Observation extends BasicEntity {

    private String description;
    private TechnicalResource observee;
    private Project relatedProject;

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }

}