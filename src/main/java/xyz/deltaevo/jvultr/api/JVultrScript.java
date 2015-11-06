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
 * Represent a Vultr Script
 */
public class JVultrScript {

    /**
     * @author DeltaEvolution
     * Represent a Script Type
     */
    public enum Type{
        BOOT,
        PXE;
    }
    private int id;
    private Date created;
    private Date modified;
    private String name;
    private Type type;
    private String script;

    /**
     * DON'T USE THIS CONSTRUCTOR !
     * @param value the JsonObject representing this object
     */
    public JVultrScript(JsonObject value){
        this.id = value.get("SCRIPTID").getAsInt();
        try {
            this.created = JVultrAPI.dateFormat.parse(value.get("date_created").getAsString());
            this.modified = JVultrAPI.dateFormat.parse(value.get("date_modified").getAsString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.name = value.get("name").getAsString();
        this.type = Type.valueOf(value.get("type").getAsString().toUpperCase());
        this.script = value.get("script").getAsString();
    }

    public JVultrScript(int id , Date created ,  Date modified , String name , Type type , String script){
        this.id = id;
        this.created = created;
        this.modified = modified;
        this.name = name;
        this.type = type;
        this.script = script;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public int getId() {
        return id;
    }

    public Date getCreated() {
        return created;
    }

    public Date getModified() {
        return modified;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public String getScript() {
        return script;
    }

    @Override
    public String toString() {
        return Reflection.toString(this);
    }
}
