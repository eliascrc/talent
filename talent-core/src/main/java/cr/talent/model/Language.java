package cr.talent.model;

import java.util.ArrayList;
import java.util.Set;

/**
 * Class that respresents a language within the Talent system.
 * It contains the language name.
 *
 * @author María José Cubero
 */
public class Language {

    /**
     * Specific language
     */
    public Set<String> languages;

    public Language(){}

    public Set<String> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<String> languages) {
        this.languages = languages;
    }
}
