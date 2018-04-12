package cr.talent.model;

import java.util.ArrayList;

public class InvitationMethod extends BasicEntity{

    private Organization organization;
    private ArrayList<Invitation> invitationList;

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }
}
