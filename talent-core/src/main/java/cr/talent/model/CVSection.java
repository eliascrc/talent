package cr.talent.model;

import java.util.ArrayList;

public class CVSection extends BasicEntity {

    private ArrayList<CVSubSection> subSections;
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