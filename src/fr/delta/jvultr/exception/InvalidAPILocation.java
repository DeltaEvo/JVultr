package fr.delta.jvultr.exception;

/**
 * Created by david on 29/10/15.
 */
public class InvalidAPILocation extends JVultrException {
    public InvalidAPILocation(String apiLocation) {
        super("Invalid API location for " + apiLocation, 400);
    }
}
