package cr.talent.model;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;


@Entity
@Table(name="paypal")
public class PayPal extends PaymentMethod {

    @Column(name="account_email", nullable = false)
    private String accountEmail;

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }

}
