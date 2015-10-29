package fr.delta.jvultr.exception;

/**
 * Created by david on 29/10/15.
 */
public class InvalidHTTPMethod extends JVultrException {
    public InvalidHTTPMethod(String method , String url) {
        super("Invalid method " + method + " for url " + url, 405);
    }
}
