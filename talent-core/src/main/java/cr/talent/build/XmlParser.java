package cr.talent.build;

import cr.talent.model.Image;
import nu.xom.*;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class XmlParser {

    private Element root;

    XmlParser(String filePath) {
        File file = new File(filePath);
        Builder parser = new Builder();
        Document document = null;
        try{
            document = parser.build(file);
        }
        catch (ParsingException ex) {
            System.err.println("Exception parsing dummy data file.");
        }
        catch (IOException ex) {
            System.err.println("Exception opening the dummy data file.");
        }

        assert document != null;
        this.root = document.getRootElement();
    }

    protected String getAttributeValue(Element parentElement, String elementName){
        return parentElement.getChildElements(elementName).get(0).getValue();
    }

    protected Elements getElementOfType(String attributeValue) {
        return root.getChildElements(attributeValue);
    }

    protected boolean getBooleanValue(Element parentElement, String elementName) {
        return parentElement.getChildElements(elementName).get(0).getValue().equals("true");
    }

    protected int getIntValue(Element parentElement, String elementName) {
        return Integer.parseInt(this.getAttributeValue(parentElement, elementName));
    }

    protected Date getDateValue(Element parentElement, String elementName){
        String attributeValue = parentElement.getChildElements(elementName).get(0).getValue();
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(attributeValue);
        }
        catch(ParseException e){
            System.out.println("Wrong date format");
        }
        return null;
    }

}
