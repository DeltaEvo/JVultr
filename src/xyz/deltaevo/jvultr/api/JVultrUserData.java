package xyz.deltaevo.jvultr.api;

import com.google.gson.JsonObject;
import xyz.deltaevo.jvultr.utils.Reflection;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Created by david on 01/11/15.
 */
public class JVultrUserData {
    String userData;
    public JVultrUserData(JsonObject value){
        userData = value.get("userdata").getAsString();
    }

    public String getUserData() {
        return userData;
    }

    public String decode(){
        try {
            return new String(Base64.getDecoder().decode(userData.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return Reflection.toString(this);
    }
}
