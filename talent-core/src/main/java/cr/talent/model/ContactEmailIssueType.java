package cr.talent.model;

import java.util.Arrays;

/**
 * Represents the issue type of a Contact Us request.
 *
 *  @author Fabián Roberto Leandro
 */
public enum ContactEmailIssueType {
    AUTHENTICATION_ISSUES("Authentication issues"),
    ACCOUNT_CLOSING("Account closing"),
    OTHER("Other");

    private String issueText;

    ContactEmailIssueType(String text) {
        this.issueText = text;
    }

    public String getIssueText(){
        return issueText;
    }

    public static boolean isValidIssueType(String issue) {
       return  Arrays.asList(this.values()).contains(issue);
    }
}
