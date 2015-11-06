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
 * @author DeltaEvolution
 * Represent a Vultr ISO
 */
public class JVultrISO {

    private int id;
    private Date created;
    private String filename;
    private long size;
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

    public int getId() {
        return id;
    }

    public Date getCreated() {
        return created;
    }

    public String getFilename() {
        return filename;
    }

    public long getSize() {
        return size;
    }

    public String getMd5() {
        return md5;
    }

    @Override
    public String toString() {
        return Reflection.toString(this);
    }
}
