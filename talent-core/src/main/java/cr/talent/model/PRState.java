package cr.talent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table ( name = "PRState" )
public abstract class PRState extends BasicEntity {

    @Column (name = "start_date")
    private Date startDate;

    @Column (name = "end_date")
    private Date endDate;

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if ( o instanceof PRState){
            PRState prState = (PRState) o;
            result = this.getId().equals(prState.getId());
        }
        return result;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }

}