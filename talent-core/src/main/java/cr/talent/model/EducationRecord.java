package cr.talent.model;

import java.io.File;
import java.util.Date;

/**
 * Class that represents an Education Record within the Talent system.
 * It contains the institution, date, title, description, resource, pdfFile
 * and the information inherited from {@link cr.talent.model.BasicEntity} class.
 *
 * @author María José Cubero
 */
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
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
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
