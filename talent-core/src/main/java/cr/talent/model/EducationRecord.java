package cr.talent.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.File;
import java.util.Date;

/**
 * Class that represents an Education Record within the Talent system.
 * It contains the institution, date, title, description, resource, pdfFile
 * and the information inherited from {@link cr.talent.model.BasicEntity} class.
 *
 * @author María José Cubero
 */
@Entity
@Table(name = "education_record")
public class EducationRecord extends BasicEntity{

    /**
     * Institution that provided the education record.
     */
    private String institution;

    /**
     * Date that the education record was given to the person.
     */
    private Date date;

    /**
     * Earned title in the education record.
     */
    private String title;

    /**
     * Description of that education record.
     */
    private String description;

    /**
     * Technical resource that has de education record.
     */
    private TechnicalResource resource;

    /**
     * File with a copy of the education record.
     */
    private File pdfFile;

    public EducationRecord(){}

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if ( o instanceof EducationRecord){
            EducationRecord educationRecord = (EducationRecord) o;
            result = (this.resource == null ? educationRecord.getResource() == null : this.resource.equals(educationRecord.getResource())
                    && this.title == null ? educationRecord.getTitle() == null : this.title.equals(educationRecord.getTitle()));
        }
        return result;
    }

    @Override
    protected int onHashCode(int result) {
        final int prime = 23;
        result = prime * result + (this.resource == null ? 0 : this.resource.hashCode());
        result = prime * result + (this.title == null ? 0 : this.title.hashCode());
        return result;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TechnicalResource getResource() {
        return resource;
    }

    public void setResource(TechnicalResource resource) {
        this.resource = resource;
    }

    public File getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(File pdfFile) {
        this.pdfFile = pdfFile;
    }
}
