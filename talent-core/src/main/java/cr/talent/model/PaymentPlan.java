package cr.talent.model;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;


@Entity
public abstract class PaymentPlan extends BasicEntity {

    @Id
    @Column(name="organization", nullable = false)
    private Organization organization;

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }

}