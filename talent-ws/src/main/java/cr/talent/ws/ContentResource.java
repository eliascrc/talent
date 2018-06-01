package cr.talent.ws;

import cr.talent.model.Platform;
import cr.talent.ws.rest.support.exceptions.UnsupportedPlatformException;

/**
 * Base class that may be extended by platform dependent resources
 *
 * @author Daniel Montes de Oca
 */
public abstract class ContentResource {

    private final String androidParameterName = "android";
    private final String webParameterName = "web";

    /**
     * Used to get the platform based on the value given to the get parameter
     * @param platformName The name of the requested platform
     * @return The enum value of the requested platform or an exception if it does not exist
     */
    protected Platform getPlatformByName(String platformName) {
        if (platformName.equals(androidParameterName)) {
            return Platform.ANDROID;
        } else if (platformName.equals(webParameterName)) {
            return Platform.WEB;
        } else {
            throw new UnsupportedPlatformException();
        }
    }

}
