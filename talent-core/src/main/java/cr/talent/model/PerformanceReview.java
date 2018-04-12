package cr.talent.model;

import java.util.ArrayList;

public class PerformanceReview extends BasicEntity {

    private PRState currentState;
    private ArrayList<PRState> states;
    private TechnicalResource resource;
    private Scale grade;

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }

}