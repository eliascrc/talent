package cr.talent.model;

public class UnauthenticatedContactEmail extends ContactEmail {

    /**
     * The first name of the person who submitted the Contact Us request.
     */
    private String firstName;

    /**
     * The last name of the person who submitted the Contact Us request.
     */
    private String lastName;

    public UnauthenticatedContactEmail() {
        super();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
