package cr.talent.model;

import java.util.ArrayList;

public class OrganizationCVSubSection extends BasicEntity {

    private ArrayList<OrganizationCVField> fields;
    private boolean enabled;
    private String name;

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }

}