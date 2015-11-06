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

import com.google.gson.JsonObject;
import xyz.deltaevo.jvultr.utils.Reflection;

/**
 * Represent a Vultr Dns Record
 * @author DeltaEvolution
 */
public class JVultrDnsRecord {

    /**
     * Represent a Vultr Dns Record Type
     * @author DeltaEvolution
     */
    public enum Type{
        A,
        MX,
        SRV,
        AAAA,
        CNAME;
    }

    /**
     * Dns Record id
     */
    private int id;

    /**
     * Dns Record type
     */
    private Type type;

    /**
     * Dns Record name
     */
    private String name;

    /**
     * Dns Record data
     */
    private String data;

    /**
     * Dns Record priority
     */
    private int priority;

    /**
     * DON'T USE THIS CONSTRUCTOR !
     * @param value the JsonObject representing this object
     */
    public JVultrDnsRecord(JsonObject value){
        this.id = value.get("RECORDID").getAsInt();
        this.type = Type.valueOf(value.get("type").getAsString());
        this.name = value.get("name").getAsString();
        this.data = value.get("data").getAsString();
        this.priority = value.get("priority").getAsInt();
    }

    /**
     * Get dns record id
     * @return dns record id
     */
    public int getId() {
        return id;
    }

    /**
     * Get dns record type
     * @return dns record type
     */
    public Type getType() {
        return type;
    }

    /**
     * Get dns name
     * @return dns name
     */
    public String getName() {
        return name;
    }

    /**
     * Get dns record data
     * @return record data
     */
    public String getData() {
        return data;
    }

    /**
     * Get dns priority
     * @return dns priority
     */
    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return Reflection.toString(this);
    }
}
