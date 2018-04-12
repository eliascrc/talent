package cr.talent.model;

import java.util.Date;

public class Invoice extends BasicEntity {

    private Date paymentDate;
    private double total;
    private Organization organization;
    private Payment associatedPayment;

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }

}