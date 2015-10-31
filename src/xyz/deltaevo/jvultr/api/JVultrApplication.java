package xyz.deltaevo.jvultr.api;

import com.google.gson.JsonObject;
import xyz.deltaevo.jvultr.utils.Reflection;

/**
 * Created by david on 30/10/15.
 */
public class JVultrApplication {

    private int id;
    private String name;
    private String shortName;
    private String deployName;

    public JVultrApplication(JsonObject value){
        this.id = value.get("APPID").getAsInt();
        this.name = value.get("name").getAsString();
        this.shortName = value.get("short_name").getAsString();
        this.deployName = value.get("deploy_name").getAsString();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getDeployName() {
        return deployName;
    }

    @Override
    public String toString() {
        return Reflection.toString(this);
    }
}
