package cr.talent.model;

public class PayPerUserInformation extends BasicEntity {

    private double costPerUser;
    private int paymentPeriod;

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }

}