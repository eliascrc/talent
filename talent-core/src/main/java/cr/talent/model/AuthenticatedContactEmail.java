package cr.talent.model;

public class AuthenticatedContactEmail extends ContactEmail {

    /**
     * The user that submitted the Contact Us request.
     */
    private User user;

    public AuthenticatedContactEmail(){
        super();
    }
}
