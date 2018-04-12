package cr.talent.model;

import java.util.List;

public class UncheckedEducationRecords extends BasicEntity{

    private List<EducationRecord> educationRecords;

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }
}
