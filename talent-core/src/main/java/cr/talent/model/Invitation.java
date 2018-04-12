package cr.talent.model;

import java.util.ArrayList;

public class Invitation extends BasicEntity {

    private String name;
    private String email;
    private JobPosition jobPosition;
    private TechnicalPosition technicalPosition;
    private ArrayList<OrganizationSkill> skills;

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }

}