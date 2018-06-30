package cr.talent.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Class that represents the previous job of a Technical Resource within the Talent system.
 * It contains the job's organization name, a description, start and end date of the job.
 * It also contains the information inherited from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Elias Calderón
 */
@Entity
@Table(name = "previous_job")
public class PreviousJob extends BasicEntity {

    /**
     * The name of the organization that the job belongs to.
     */
    @Column(name = "organization_name", nullable = false)
    private String organizationName;

    /**
     * A description of the job.
     */
    @Column(name = "description")
    private String description;

    /**
     * The start date of the job.
     */
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    /**
     * The end date of the job.
     */
    @Column(name = "end_date", nullable = false)
    private Date endDate;

    /**
     * The technical resource to whom the work belongs.
     */
    @ManyToOne
    @JoinColumn(name = "technical_resource_id", nullable = false)
    private TechnicalResource technicalResource;

    public PreviousJob() {}

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if (o instanceof PreviousJob){
            PreviousJob previousJob = (PreviousJob) o;
            result = (this.organizationName == null ? previousJob.getOrganizationName() == null : this.organizationName.equals(previousJob.getOrganizationName())
                    && this.technicalResource == null ? previousJob.getTechnicalResource() == null : this.technicalResource.equals(previousJob.getTechnicalResource())
                    && this.startDate == null ? previousJob.getStartDate() == null : this.startDate.equals(previousJob.getStartDate()));
        }
        return result;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }

    public TechnicalResource getTechnicalResource() {
        return technicalResource;
    }

    public void setTechnicalResource(TechnicalResource technicalResource) {
        this.technicalResource = technicalResource;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
