package xyz.deltaevo.jvultr.api;

import com.google.gson.JsonObject;
import xyz.deltaevo.jvultr.JVultrAPI;
import xyz.deltaevo.jvultr.utils.Reflection;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by david on 31/10/15.
 */
public class JVultrDns {

    private String domain;
    private Date created;

    public JVultrDns(JsonObject value){
        this.domain = value.get("domain").getAsString();
        try {
            this.created = JVultrAPI.dateFormat.parse(value.get("date_created").getAsString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getDomain() {
        return domain;
    }

    public Date getCreated() {
        return created;
    }

    @Override
    public String toString() {
        return Reflection.toString(this);
    }
}
