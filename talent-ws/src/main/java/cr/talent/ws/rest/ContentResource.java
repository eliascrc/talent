package cr.talent.ws.rest;

import cr.talent.model.Platform;
import cr.talent.ws.rest.support.exceptions.UnsupportedPlatformException;

/**
 * Base class that may be extended by platform dependent resources
 *
 * @author Daniel Montes de Oca
 */
public abstract class ContentResource {


    private static final String ANDROID_PARAMETER_NAME = "android";
    private static final String WEB_PARAMETER_NAME = "web";

    /**
     * Used to get the platform based on the value given to the get parameter
     * @param platformName The name of the requested platform
     * @return The enum value of the requested platform or an exception if it does not exist
     */
    protected Platform getPlatformByName(String platformName) {
        if (platformName.equals(ANDROID_PARAMETER_NAME)) {
            return Platform.ANDROID;
        } else if (platformName.equals(WEB_PARAMETER_NAME)) {
            return Platform.WEB;
        } else {
            throw new UnsupportedPlatformException();
        }
    }

}
