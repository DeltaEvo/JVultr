package xyz.deltaevo.jvultr.api;

import com.google.gson.JsonObject;
import xyz.deltaevo.jvultr.JVultrAPI;
import xyz.deltaevo.jvultr.utils.Reflection;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by david on 31/10/15.
 */
public class JVultrISO {

    private int id;
    private Date created;
    private String filename;
    private long size;
    private String md5;

    public JVultrISO(JsonObject value){
        this.id = value.get("ISOID").getAsInt();
        try {
            this.created = JVultrAPI.dateFormat.parse(value.get("date_created").getAsString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.filename = value.get("filename").getAsString();
        this.size = value.get("size").getAsLong();
        this.md5 = value.get("md5sum").getAsString();
    }

    public int getId() {
        return id;
    }

    public Date getCreated() {
        return created;
    }

    public String getFilename() {
        return filename;
    }

    public long getSize() {
        return size;
    }

    public String getMd5() {
        return md5;
    }

    @Override
    public String toString() {
        return Reflection.toString(this);
    }
}
