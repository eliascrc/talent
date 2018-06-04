package cr.talent.model;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "sign_up_confirmation_message")
public class SignUpConfirmationMessage {

    /**
     * It represents the email address of the user performing the sign up.
     */
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    /**
     * The code that the user will have to match to continue the sign up process
     */
    @Column(name = "confirmation_code", nullable = false)
    private String confirmationCode;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }
}
