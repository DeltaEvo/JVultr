package xyz.deltaevo.jvultr.api;

import com.google.gson.JsonObject;
import xyz.deltaevo.jvultr.JVultrAPI;
import xyz.deltaevo.jvultr.utils.Reflection;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by david on 29/10/15.
 */
public class JVultrSnapshot {
    public enum Status{
        PENDING,
        COMPLETE;
    }
    private String id;
    private Date created;
    private String description;
    private long size;
    private Status status;

    public JVultrSnapshot(JsonObject value) {
        this.id = value.get("SNAPSHOTID").getAsString();
        try {
            this.created = JVultrAPI.dateFormat.parse(value.get("date_created").getAsString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.description = value.get("description").getAsString();
        this.size = value.get("size").getAsLong();
        this.status = Status.valueOf(value.get("status").getAsString().toUpperCase());
    }

    public String getId() {
        return id;
    }

    public Date getCreated() {
        return created;
    }

    public String getDescription() {
        return description;
    }

    public long getSize() {
        return size;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return Reflection.toString(this);
    }
}