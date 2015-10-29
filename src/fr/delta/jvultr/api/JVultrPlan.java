package fr.delta.jvultr.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Arrays;

/**
 * Created by david on 29/10/15.
 */
public class JVultrPlan {
    public enum PlanType{
        SSD,
        DEDICATED,
        SATA;
    }
    private int id;
    private String name;
    private int cpus;
    private int ram;
    private int disk;
    private float bandwidth;
    private float pricePerMonth;
    private boolean windows;
    private PlanType type;
    private int[] availableLocations;

    public JVultrPlan(JsonObject value) {
        this.id = value.get("VPSPLANID").getAsInt();
        this.name = value.get("name").getAsString();
        this.cpus = value.get("vcpu_count").getAsInt();
        this.ram = value.get("ram").getAsInt();
        this.disk = value.get("disk").getAsInt();
        this.bandwidth = value.get("bandwidth").getAsFloat();
        this.pricePerMonth = value.get("price_per_month").getAsFloat();
        this.windows = value.get("windows").getAsBoolean();
        this.type = PlanType.valueOf(value.get("plan_type").getAsString());
        if(value.has("available_location")){
            JsonArray array = value.get("available_location").getAsJsonArray();
            availableLocations = new int[array.size()];
            int i = 0;
            for(JsonElement element : array){
                availableLocations[i] = element.getAsInt();
                i++;
            }
        }else{
             availableLocations = new int[0];
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCpus() {
        return cpus;
    }

    public int getRam() {
        return ram;
    }

    public int getDisk() {
        return disk;
    }

    public float getBandwidth() {
        return bandwidth;
    }

    public float getPricePerMonth() {
        return pricePerMonth;
    }

    public boolean isWindows() {
        return windows;
    }

    public PlanType getType() {
        return type;
    }

    public int[] getAvailableLocations() {
        return availableLocations;
    }

    @Override
    public String toString() {
        return "id:" + id + ",name:" + name + ",cpus:"
                + cpus + ",ram:" + ram + ",disk:" + disk + ",bandwidth:"
                + bandwidth + ",pricePerMonth:" + pricePerMonth + ",windows:" + windows
                + ",type:" + type + ",availableLocations:" + Arrays.toString(availableLocations) + "\n";
    }
}
