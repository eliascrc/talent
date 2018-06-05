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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "image")
public class Image extends BasicEntity {

    /**
     * Link to get to the image.
     */
    @Column (name = "link")
    private String link;

	public Image(){}
		
    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if ( o instanceof Image){
            Image image = (Image) o;
            result = (this.link == null ? image.getLink() == null : this.link.equals(image.getLink()));
        }
        return result;
    }

    @Override
    protected int onHashCode(int result) {
        final int prime = 23;
        result = prime * result + (this.link == null ? 0 : this.link.hashCode());
        return result;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}