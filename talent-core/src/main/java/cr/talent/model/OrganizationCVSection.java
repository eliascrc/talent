package cr.talent.model;

import java.util.ArrayList;

public class OrganizationCVSection extends BasicEntity {

    private ArrayList<OrganizationCVSubSection> subSections;
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