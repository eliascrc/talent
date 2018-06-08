package cr.talent.model;

import java.util.Arrays;

/**
 * Represents the issue type of a Contact Us request.
 *
 *  @author Fabián Roberto Leandro
 */
public enum ContactUsIssueType {
    AUTHENTICATION_ISSUES("Authentication issues"),
    ACCOUNT_CLOSING("Account closing"),
    OTHER("Other");

    private String issueText;

    ContactUsIssueType(String text) {
        this.issueText = text;
    }

    public String getIssueText(){
        return issueText;
    }

    public static ContactUsIssueType fromString(String issueType){
        for(ContactUsIssueType type : values() ) {
            if(type.getIssueText().equals(issueType))
                return type;
        }
       return  null;
    }
}
