package fr.delta.jvultr.exception;

/**
 * Created by david on 29/10/15.
 */
public class RequestFailed extends JVultrException {
    public RequestFailed() {
        super("Request failed", 412);
    }

    public RequestFailed(Exception ex) {
        super("Request failed", 412 , ex);
    }
}
