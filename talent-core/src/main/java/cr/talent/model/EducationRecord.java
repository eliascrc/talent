package cr.talent.model;

import javax.persistence.*;
import java.io.File;
import java.util.Date;

/**
 * Class that represents an Education Record within the Talent system.
 * It contains the institution, start and end date, title, description, level, resource, pdfFile
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
    @Column(name = "institution")
    private String institution;

    /**
     * Date that the resource started the training.
     */
    @Column (name = "startDate")
    private Date startDate;

    /**
     * Date that the resource ended the training.
     */
    @Column (name = "endDate")
    private Date endDate;

    /**
     * Earned title in the education record.
     */
    @Column (name = "title" , nullable = false)
    private String title;

    /**
     * Description of that education record.
     */
    @Column (name = "description")
    private String description;

    /**
     * The level of the record.
     */
    @Column (name = "level")
    private String level;

    /**
     * Technical resource that has de education record.
     */
    @ManyToOne
    @JoinColumn(name = "resource_id", nullable = false)
    private TechnicalResource resource;

    /**
     * File with a copy of the education record.
     */
    @Column (name = "pdf_file")
    private File pdfFile;

    /**
     * The human resource manager that is going to review the education record
     */
    @ManyToOne
    @JoinColumn (name = "HRM_id", nullable = false)
    private HumanResourceManager humanResourceManager;

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
        return startDate;
    }

    public void setDate(Date date) {
        this.startDate = date;
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

    public HumanResourceManager getHumanResourceManager() {
        return humanResourceManager;
    }

    public void setHumanResourceManager(HumanResourceManager humanResourceManager) {
        this.humanResourceManager = humanResourceManager;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
