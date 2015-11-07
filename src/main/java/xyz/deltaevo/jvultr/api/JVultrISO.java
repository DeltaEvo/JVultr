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
 * Represent a Vultr ISO
 * @author DeltaEvolution
 */
public class JVultrISO {

    /**
     * Vultr ISO id
     */
    private int id;

    /**
     * ISO creation date
     */
    private Date created;

    /**
     * ISO filename
     */
    private String filename;

    /**
     * ISO file size
     */
    private long size;

    /**
     * ISO file MD5
     */
    private String md5;

    /**
     * DON'T USE THIS CONSTRUCTOR !
     * @param value the JsonObject representing this object
     */
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

    /**
     * Get Vultr ISO id
     * @return iso id
     */
    public int getId() {
        return id;
    }

    /**
     * Get ISO creation date
     * @return creation date
     */
    public Date getCreated() {
        return created;
    }

    /**
     * Get ISO file name
     * @return file name
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Get ISO file size
     * @return file size
     */
    public long getSize() {
        return size;
    }

    /**
     * Get ISO file MD5
     * @return file MD5
     */
    public String getMd5() {
        return md5;
    }

    @Override
    public String toString() {
        return Reflection.toString(this);
    }
}
