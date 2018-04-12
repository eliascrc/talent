package cr.talent.model;

public class ContactEmail extends BasicEntity {

    private String email;
    private String subject;
    private String content;

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }

}