package cr.talent.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table ( name = "reviwer")
public class Reviewer extends TechnicalResource{

    @OneToMany
    private List<LATest> tests;



}
