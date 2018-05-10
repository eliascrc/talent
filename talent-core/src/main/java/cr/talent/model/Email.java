package cr.talent.model;


/**
 * Class that represents an email to send in the Talent System.
 * It has the basic email information.
 *
 * @author María José Cubero
 */

public class Email {

    /**
     * Who is going to send the message.
     */
    private String from;

    /**
     * Where the message is going to be send.
     */
    private String to;

    /**
     * The subject of the email.
     */
    private String subject;

    /**
     * The message of the email.
     */
    private String content;

    /**
     * HTML file for velocity that is going to be send.
     */
    private String fileName;

    public Email() {
    }

    @Override
    public String toString() {
        return "Email{" +
                "from='" + this.getFrom() + '\'' +
                ", to='" + this.getTo() + '\'' +
                ", subject='" + this.getSubject() + '\'' +
                ", content='" + this.getContent() + '\'' +
                ", filename='" + this.getFileName() + '\'' +
                '}';
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
