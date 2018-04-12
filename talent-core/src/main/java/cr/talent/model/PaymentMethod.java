package cr.talent.model;

import javax.persistence.Entity;

@Entity
public abstract class PaymentMethod extends BasicEntity{

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }

}
