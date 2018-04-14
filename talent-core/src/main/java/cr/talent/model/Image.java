package cr.talent.model;

import javax.persistence.*;
import java.util.*;


/**
 * Class that represents an image within the Talent system.
 * It contains the link of the image and the information inherited from
 * {@link cr.talent.model.BasicEntity} class.
 *
 * @author María José Cubero
 */
@Entity
@Table(name = "image")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Image extends BasicEntity {

    /**
     * Link to get to the image.
     */
	@Column(name = "link")
    private String link;

	public Image(){}
		
    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}