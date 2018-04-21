package cr.talent.build;

import nu.xom.*;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class reads the xml file and gets its root which will then be used to navigate the rest of the file.
 *
 * @author Daniel Montes de Oca & Otto Mena Kikut
 */
public class XmlParser {

    private Element root;

    /**
     * The constructor of this class creates a File object which then will then will be used by the Builder object name parser of the xom library,
     * then the document gets the root of the file. The exceptions corresponding to an incorrect xml format or an io exception are catched.
     *
     * @param filePath the path of the xml file.
     */
    XmlParser(String filePath) {
        File file = new File(filePath);
        Builder parser = new Builder();
        Document document = null;
        System.out.println(filePath);
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

    /**
     * This method reads part of the xml file that should be interpreted as a String.
     *
     * @param parentElement this is the node of the xml file which contains all the attributes corresponding to an object.
     * @param elementName this is the name of the attribute that we are interested in reading.
     * @return the String of the corresponding elementName
     */
    protected String getAttributeValue(Element parentElement, String elementName){
        return parentElement.getChildElements(elementName).get(0).getValue();
    }

    /**
     * This method gets the children of the root of the file.
     *
     * @param attributeValue the name of the children we want to return.
     * @return the child of that has the attributeValue name.
     */
    protected Elements getElementOfType(String attributeValue) {
        return root.getChildElements(attributeValue);
    }

    /**
     * This method reads a part of the xml file that should be interpreted as a Boolean.
     *
     * @param parentElement this is the node of the xml file which contains all the attributes corresponding to an object.
     * @param elementName this is the name of the attribute that we are interested in reading.
     * @return the Boolean of the corresponding elementName
     */
    protected boolean getBooleanValue(Element parentElement, String elementName) {
        return parentElement.getChildElements(elementName).get(0).getValue().equals("true");
    }

    /**
     * This class reads a part of the xml file and returns an Integer type.
     *
     * @param parentElement this is the node of the xml file which contains all the attributes corresponding to an object.
     * @param elementName this is the name of the attribute that we are interested in reading.
     * @return the Integer of the corresponding elementName
     */
    protected int getIntValue(Element parentElement, String elementName) {
        return Integer.parseInt(this.getAttributeValue(parentElement, elementName));
    }

    /**
     * This class reads a part of the xml file and return a Date.
     *
     * @param parentElement this is the node of the xml file which contains all the attributes corresponding to an object.
     * @param elementName this is the name of the attribute that we are interested in reading.
     * @return the Date of the corresponding elementName
     */
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
