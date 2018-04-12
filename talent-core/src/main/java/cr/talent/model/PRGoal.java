package cr.talent.model;

public class PRGoal extends BasicEntity {

    private String goal;
    private Boolean approved;
    private String feedback;

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }

}