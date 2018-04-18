package cr.talent.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Set;

/**
 * Class that respresents a language within the Talent system.
 * It contains the language name.
 *
 * @author María José Cubero
 */
@Entity
@Table(name = "language")
public class Language extends BasicEntity{

    /**
     * Specific language
     */
    private Language languageName;

    public Language(){}

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if ( o instanceof Language){
            Language language = (Language) o;
            result = (this.languageName == null ? language.getLanguageName() == null : this.languageName.equals(language.getLanguageName()));
        }
        return result;
    }

    @Override
    protected int onHashCode(int result) {
        final int prime = 23;
        result = prime * result + (this.languageName == null ? 0 : this.languageName.hashCode());
        return result;
    }

    public Language getLanguageName() {
        return languageName;
    }

    public void setLanguageName(Language languageName) {
        this.languageName = languageName;
    }
}
