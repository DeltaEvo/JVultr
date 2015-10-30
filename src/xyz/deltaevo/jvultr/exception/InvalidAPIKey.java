package xyz.deltaevo.jvultr.exception;

/**
 * Created by david on 29/10/15.
 */
public class InvalidAPIKey extends JVultrException {
    public InvalidAPIKey() {
        super("Invalid or missing API key", 403);
    }
}
