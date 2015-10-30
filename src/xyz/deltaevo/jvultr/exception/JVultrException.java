package xyz.deltaevo.jvultr.exception;

/**
 * Created by david on 29/10/15.
 */
public class JVultrException extends Exception{
    private final int responceCode;

    public JVultrException(String cause, int responseCode){
        super(cause + " | Http Response code " + responseCode);
        this.responceCode = responseCode;
    }

    public JVultrException(String cause, int responseCode, Exception ex){
        super(cause + " | Http Response code " + responseCode , ex);
        this.responceCode = responseCode;
    }

    public int getResponceCode() {
        return responceCode;
    }
}
