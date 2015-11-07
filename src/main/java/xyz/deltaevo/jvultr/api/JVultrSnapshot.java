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
import xyz.deltaevo.jvultr.JVultrAPI;
import xyz.deltaevo.jvultr.utils.Reflection;

import java.text.ParseException;
import java.util.Date;

/**
 * Represent a Vultr Snapshot
 * @author DeltaEvolution
 */
public class JVultrSnapshot {

    /**
     * Represent a Vultr Snapshot Status
     * @author DeltaEvolution
     */
    public enum Status{
        PENDING,
        COMPLETE;
    }

    /**
     * Vultr snapshot id
     */
    private String id;

    /**
     * Snapshot creation date
     */
    private Date created;

    /**
     * Snapshot description
     */
    private String description;

    /**
     * Snapshot size
     */
    private long size;

    /**
     * Snapshot status
     * @see JVultrSnapshot.Status
     */
    private Status status;

    /**
     * DON'T USE THIS CONSTRUCTOR !
     * @param value the JsonObject representing this object
     */
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

    /**
     * Get Vultr snapshot id
     * @return snapshot id
     */
    public String getId() {
        return id;
    }

    /**
     * Get snapshot creation date
     * @return creation date
     */
    public Date getCreated() {
        return created;
    }

    /**
     * Get snapshot description
     * @return snapshot description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get snapshot size
     * @return snapshot size
     */
    public long getSize() {
        return size;
    }

    /**
     * Get snapshot status
     * @return snapshot status
     */
    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return Reflection.toString(this);
    }
}
