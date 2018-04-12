package cr.talent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table ( name = "PRPhaseTwo")
public class PRPhaseTwo extends PRState {

    @Column ( name = "resource" )
    private TechnicalResource resource;

    @OneToMany
    private List<PRFeedback> peerFeedback;

    public TechnicalResource getResource() {
        return resource;
    }

    public List<PRFeedback> getPeerFeedback() {
        return peerFeedback;
    }
}