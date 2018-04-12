package cr.talent.model;

public class PRAchievement extends BasicEntity {

    private String achievement;
    private Scale scale;

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }

}
