package cr.talent.model;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Column;

@Entity
@Table(name="payment")
public class Payment extends BasicEntity{

    @Column(name="payment_state", nullable = false)
    private PaymentState paymentState;
    @Column(name="payment_invoice", nullable = false)
    private Invoice paymentInvoice;
    @Column(name="payment_method", nullable = false)
    private PaymentMethod paymentMethod;
    @Column(name="payment_plan", nullable = false)
    private PaymentPlan paymentPlan;

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }
}
