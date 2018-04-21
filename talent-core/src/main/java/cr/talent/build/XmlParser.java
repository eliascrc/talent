package cr.talent.build;

import cr.talent.model.Image;
import nu.xom.*;

import java.io.File;
import java.io.IOException;
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
            System.err.println("Exception parsing dummy data file");
        }
        catch (IOException ex) {
            System.err.println(ex);
        }

        this.root = document.getRootElement();
    }

    protected String getAttributeValue(Element parentElement, String elementName){
        String attributeValue = parentElement.getChildElements(elementName).get(0).getValue();
        return attributeValue;
    }

    protected Elements getElementOfType(String attributeValue) {
        return root.getChildElements(attributeValue);
    }

    protected boolean getBooleanValue(Element parentElement, String elementName) {
        boolean value;
        if (parentElement.getChildElements(elementName).get(0).getValue() == "true") {
            value = true;
        } else {
            value = false;
        }

        return value;
    }

    protected int getIntValue(Element parentElement, String elementName) {
        return Integer.parseInt(this.getAttributeValue(parentElement, elementName));
    }

    protected Image getImage(Element parentElement, String imageElementName){
        String attributeValue = parentElement.getChildElements(imageElementName).get(0).getValue();
        Image image = new Image();
        image.setLink(attributeValue);
        return image;
    }
}
