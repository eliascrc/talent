package cr.talent.model;

/**
 * Class that represents a technical's resource emergency contact within the Talent system.
 * It contains the email, name, telephone and the information inherited from
 * {@link cr.talent.model.BasicEntity} class.
 *
 * @author Elías Calderon
 */
public class EmergencyContact extends BasicEntity{

    /**
     * Email of the emergency contact.
     */
    private String email;

    /**
     * Name of the emergency contact.
     */
    private String name;

    /**
     * Telephone of the emergency contact.
     */
    private String telephone;

    /**
     * User that has the emergency contact.
     */
    private TechnicalResource technicalResource;

    public EmergencyContact(){}

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public TechnicalResource getTechnicalResource() {
        return technicalResource;
    }

    public void setTechnicalResource(TechnicalResource technicalResource) {
        this.technicalResource = technicalResource;
    }
}
