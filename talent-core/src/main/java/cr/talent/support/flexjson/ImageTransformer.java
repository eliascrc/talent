package cr.talent.support.flexjson;

import cr.talent.model.Image;
import flexjson.transformer.AbstractTransformer;

/**
 * Used to transform an image, so that it can be included in the json of an organization or technical resource
 */
public class ImageTransformer extends AbstractTransformer {

    /**
     * Writes the image link into the json if it is an image and it is not null
     * @param value The image that has the link
     */
    public void transform(Object value) {
        if (value == null || !(value instanceof Image)) {
            // do not make the logo a part of the json
            getContext().write("null");
        } else {
            getContext().writeQuoted(((Image) value).getLink());
        }
    }

}
