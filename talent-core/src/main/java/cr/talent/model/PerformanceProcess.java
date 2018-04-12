package cr.talent.model;

import java.util.ArrayList;

public class PerformanceProcess extends BasicEntity {

    private ArrayList<PerformanceReview> performanceReviews;

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }

}