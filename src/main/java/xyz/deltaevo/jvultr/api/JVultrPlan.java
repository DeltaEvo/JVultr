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
 * Represent a Vultr Plan
 * @author DeltaEvolution
 */
public class JVultrPlan {

    /**
     * Represent a Vultr Plan Type
     * @author DeltaEvolution
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

    /**
     * Vultr plan id
     */
    private int id;

    /**
     * Plan name
     */
    private String name;

    /**
     * Plan cpu amount
     */
    private int cpus;

    /**
     * Plan ram amount
     */
    private int ram;

    /**
     * Plan disk space
     */
    private int disk;

    /**
     * Plan bandwidth
     */
    private float bandwidth;

    /**
     * Plan price/month
     */
    private float pricePerMonth;

    /**
     * Plan is windows ?
     */
    private boolean windows;

    /**
     * Plan type
     */
    private Type type;

    /**
     * Plan available regions
     */
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

    /**
     * Get Vultr Plan id
     * @return plan id
     */
    public int getId() {
        return id;
    }

    /**
     * Get plan name
     * @return plan name
     */
    public String getName() {
        return name;
    }

    /**
     * Get plan cpu amount
     * @return cpu amount
     */
    public int getCpus() {
        return cpus;
    }

    /**
     * Get plan ram amount
     * @return ram amount
     */
    public int getRam() {
        return ram;
    }

    /**
     * Get plan disk space
     * @return disk space
     */
    public int getDisk() {
        return disk;
    }

    /**
     * Get plan bandwidth
     * @return plan bandwidth
     */
    public float getBandwidth() {
        return bandwidth;
    }

    /**
     * Get plan price per month
     * @return plan price per month
     */
    public float getPricePerMonth() {
        return pricePerMonth;
    }

    /**
     * Get if plan is windows ?
     * @return is windows ?
     */
    public boolean isWindows() {
        return windows;
    }

    /**
     * Get plan type
     * @return plan type
     */
    public Type getType() {
        return type;
    }

    /**
     * Get plan available regions
     * @return plan available regions
     */
    public JVultrRegion[] getAvailableRegions() {
        return availableRegions;
    }

    @Override
    public String toString() {
        return Reflection.toString(this);
    }
}
