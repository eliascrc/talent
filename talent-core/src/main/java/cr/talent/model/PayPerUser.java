package cr.talent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="pay_per_user")
public class PayPerUser extends PaymentPlan {

    @Column(name="pey_per_user_information", nullable = false)
    private PayPerUserInformation information;

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }
}
