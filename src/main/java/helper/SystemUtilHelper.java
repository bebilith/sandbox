package helper;

import org.lwjgl.Sys;

/**
 * Created by till on 4/24/14.
 */
public class SystemUtilHelper {

    /**
     * Get the time in milliseconds
     *
     * @return The system time in milliseconds
     */
    public static long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }
}
