package xyz.deltaevo.jvultr.api;

import com.google.gson.JsonObject;
import xyz.deltaevo.jvultr.utils.Reflection;

/**
 * Created by david on 31/10/15.
 */
public class JVultrDnsRecord {
    public enum Type{
        A,
        CNAME;
    }

    private int id;
    private Type type;
    private String name;
    private String data;
    private int priority;

    public JVultrDnsRecord(JsonObject value){
        this.id = value.get("RECORDID").getAsInt();
        this.type = Type.valueOf(value.get("type").getAsString());
        this.name = value.get("name").getAsString();
        this.data = value.get("data").getAsString();
        this.priority = value.get("priority").getAsInt();
    }

    public int getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return Reflection.toString(this);
    }
}
