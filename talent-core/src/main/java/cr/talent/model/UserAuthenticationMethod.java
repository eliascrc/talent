package cr.talent.model;

public abstract class UserAuthenticationMethod extends BasicEntity {

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }

}