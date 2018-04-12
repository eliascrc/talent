package cr.talent.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "image")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Image extends BasicEntity {

	@Column(name = "link")
    private String link;
	
	public String getLink() {
		return this.link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
		
    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }

}