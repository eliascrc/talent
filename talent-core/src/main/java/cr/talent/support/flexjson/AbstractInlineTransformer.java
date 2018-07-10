package cr.talent.support.flexjson;

import cr.talent.support.exceptions.FieldNameNotSetException;
import flexjson.transformer.AbstractTransformer;

/**
 * Provides basic functionality of an inline flexjson transformer, such as writing a comma if ncessary or writing the
 * field as null.
 *
 * @author Fabián Roberto Leandro
 */
public abstract class AbstractInlineTransformer extends AbstractTransformer {

    // The name of the field to be written. Child classes should set it in the constructor.
    protected String fieldName;

    /**
     * Writes a comma (if this field isn't the first one in an array) and the project lead field name
     */
    protected void writeJsonStart() {
        if(fieldName==null)
            throw new FieldNameNotSetException();

        // Write a comma if necessary
        if (!this.getContext().peekTypeContext().isFirst())
            this.getContext().writeComma();

        // Write the project lead field name
        this.getContext().writeName(this.fieldName);
    }

    /**
     * Writes the project lead with the null value
     */
    protected void writeNull() {
        this.writeJsonStart();

        // write the "null" string
        this.getContext().write("null");
    }

    /**
     * Set this to true in order to tell flexjson that we will generate the json for the project position
     * This allows us to change the property's name from 'leadHistory' to projectLead
     */
    @Override
    public Boolean isInline() {
        return Boolean.TRUE;
    }
}
