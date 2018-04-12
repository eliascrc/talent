package cr.talent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table ( name = "PRPhaseThree")
public class PRPhaseThree extends PRState {

    @OneToMany
    private List<PMFeedback> pmFeedbacks;

    @Column (name = "organization")
    private Organization organization;

    public List<PMFeedback> getPmFeedbacks() {
        return pmFeedbacks;
    }

    public Organization getOrganization() {
        return organization;
    }
}
