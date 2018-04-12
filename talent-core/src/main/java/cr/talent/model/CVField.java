package cr.talent.model;

public class CVField extends BasicEntity {

    private String name;
    private String content;
    private boolean enabled;

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }

}