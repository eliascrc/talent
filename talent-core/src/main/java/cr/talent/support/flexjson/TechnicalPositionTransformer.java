package cr.talent.support.flexjson;

import cr.talent.model.TechnicalPosition;
import flexjson.transformer.AbstractTransformer;

/**
 * Transforms a technical position to write the names of its capability and capability level.
 *
 * @author Fabián Roberto Leandro
 */
public class TechnicalPositionTransformer extends AbstractInlineTransformer {

    public TechnicalPositionTransformer(){
        fieldName="technicalPosition";
    }

    /**
     * If the received object is an instance of TechnicalPosition, get its capability and capability level to write the
     * name
     *
     * @param object
     */
    public void transform(Object object) {

        // If object is not an instance of technical position then write the technical position entry as null
        if(object==null || !(object instanceof TechnicalPosition)) {
            this.writeNull();
            return;
        }

        TechnicalPosition technicalPosition = (TechnicalPosition) object;

        // Build the string with the capability and capabilityLevel names
        String technicalPositionName=technicalPosition.getCapabilityLevel().getName()+" "+technicalPosition.getCapabilityLevel().getCapability().getName();

        // Write the json
        this.writeJsonStart();
        this.getContext().writeQuoted(technicalPositionName);

    }
}
