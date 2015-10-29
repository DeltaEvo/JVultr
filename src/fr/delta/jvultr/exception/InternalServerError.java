package fr.delta.jvultr.exception;

/**
 * Created by david on 29/10/15.
 */
public class InternalServerError extends JVultrException {
    public InternalServerError() {
        super("Internal server error. Try again at a later time", 500);
    }
}
