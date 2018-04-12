package cr.talent.model;

import java.util.List;

public class CareerPath extends BasicEntity {

    private List<TechnicalPosition> positions;

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }

}