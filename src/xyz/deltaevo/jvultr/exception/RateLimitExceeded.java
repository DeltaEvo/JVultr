package xyz.deltaevo.jvultr.exception;

/**
 * Created by david on 29/10/15.
 */
public class RateLimitExceeded extends JVultrException {

    public RateLimitExceeded() {
        super("Rate limit exceeded. API requests are limited to an average of 1/s. Try your request again later", 503);
    }
}
