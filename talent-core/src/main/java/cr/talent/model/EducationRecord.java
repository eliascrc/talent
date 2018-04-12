package cr.talent.model;

import java.io.File;
import java.util.Date;

public class EducationRecord extends BasicEntity{

    private String institution;
    private Date date;
    private String title;
    private String description;
    private TechnicalResource resource;
    private File pdfFile;

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }
}
