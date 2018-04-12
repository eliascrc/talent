package cr.talent.model;

public class FreeInformation extends BasicEntity {

    private int maximumUsers;

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }

}