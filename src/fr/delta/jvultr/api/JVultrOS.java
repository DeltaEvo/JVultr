package fr.delta.jvultr.api;

import com.google.gson.JsonObject;

/**
 * Created by david on 29/10/15.
 */
public class JVultrOS {
    public enum Arch{
        x64,
        i386;
    }

    private int id;
    private String name;
    private Arch arch;
    private String family;
    private boolean windows;

    public JVultrOS(JsonObject value) {
        this.id = value.get("OSID").getAsInt();
        this.name = value.get("name").getAsString();
        this.arch = Arch.valueOf(value.get("arch").getAsString());
        this.family = value.get("family").getAsString();
        this.windows = value.get("windows").getAsBoolean();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Arch getArch() {
        return arch;
    }

    public String getFamily() {
        return family;
    }

    public boolean isWindows() {
        return windows;
    }

    @Override
    public String toString() {
        return "id:" + id + ",name:" + name + ",arch:" + arch + ",family:" + family + ",windows:" + windows + "\n";
    }
}
