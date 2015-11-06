/*
 * Copyright 2015 DeltaEvolution
 *
 * This file is part of JVultr.
 * JVultr is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JVultr is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JVultr. If not, see <http://www.gnu.org/licenses/>.
 */
package xyz.deltaevo.jvultr.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import xyz.deltaevo.jvultr.JVultrCache;
import xyz.deltaevo.jvultr.utils.Reflection;

import java.util.Arrays;

/**
 * @author DeltaEvolution
 * Represent a Vultr Plan
 */
public class JVultrPlan {

    /**
     * @author DeltaEvolution
     * Represent a Vultr Plan Type
     */
    public enum Type{
        /**
         * SSD Plan Type
         */
        SSD,
        /**
         * Dedicated Plan Type
         */
        DEDICATED,
        /**
         * Sata Plan Type
         */
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
    private Type type;
    private JVultrRegion[] availableRegions;

    /**
     * DON'T USE THIS CONSTRUCTOR !
     * @param value the JsonObject representing this object
     */
    public JVultrPlan(JsonObject value) {
        this.id = value.get("VPSPLANID").getAsInt();
        this.name = value.get("name").getAsString();
        this.cpus = value.get("vcpu_count").getAsInt();
        this.ram = value.get("ram").getAsInt();
        this.disk = value.get("disk").getAsInt();
        this.bandwidth = value.get("bandwidth").getAsFloat();
        this.pricePerMonth = value.get("price_per_month").getAsFloat();
        this.windows = value.get("windows").getAsBoolean();
        this.type = Type.valueOf(value.get("plan_type").getAsString());
        if(value.has("available_locations")){
            JsonArray array = value.get("available_locations").getAsJsonArray();
            availableRegions = new JVultrRegion[array.size()];
            int i = 0;
            for(JsonElement element : array){
                availableRegions[i] = JVultrCache.getCachedRegion(element.getAsInt());
                i++;
            }
        }else{
             availableRegions = new JVultrRegion[0];
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

    public Type getType() {
        return type;
    }

    public JVultrRegion[] getAvailableRegions() {
        return availableRegions;
    }

    @Override
    public String toString() {
        return Reflection.toString(this);
    }
}
