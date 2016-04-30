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
 * Represent a Vultr Script
 * @author DeltaEvolution
 */
public class JVultrScript {

    /**
     * Represent a Script Type
     * @author DeltaEvolution
     */
    public enum Type{
        BOOT,
        PXE;
    }

    /**
     * Vultr Script id
     */
    private int id;

    /**
     * Script creation date
     */
    private Date created;

    /**
     * Script lastModification date
     */
    private Date modified;

    /**
     * Script name
     */
    private String name;

    /**
     * Script type
     */
    private Type type;

    /**
     * Script content
     */
    private String script;

    /**
     * DON'T USE THIS CONSTRUCTOR !
     * @param value the JsonObject representing this object
     */
    public JVultrScript(JsonObject value){
        this.id = value.get("SCRIPTID").getAsInt();
        try {
            this.created = JVultrAPI.DATE_FORMAT.parse(value.get("date_created").getAsString());
            this.modified = JVultrAPI.DATE_FORMAT.parse(value.get("date_modified").getAsString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.name = value.get("name").getAsString();
        if(value.has("type"))
            this.type = Type.valueOf(value.get("type").getAsString().toUpperCase());
        this.script = value.get("script").getAsString();
    }

    /**
     * DON'T USE THIS CONSTRUCTOR !
     * @param id Vultr script id
     * @param created Script created date
     * @param modified Script last modification date
     * @param name Script name
     * @param type Script type
     * @param script Script content
     */
    public JVultrScript(int id , Date created ,  Date modified , String name , Type type , String script){
        this.id = id;
        this.created = created;
        this.modified = modified;
        this.name = name;
        this.type = type;
        this.script = script;
    }

    /**
     * THIS METHOD DO NOT MODIFY ON VULTR WEBSITE
     * <p>Set script name</p>
     * @param name script name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * THIS METHOD DO NOT MODIFY ON VULTR WEBSITE
     * <p>Set script content</p>
     * @param script script content
     */
    public void setScript(String script) {
        this.script = script;
    }

    /**
     * THIS METHOD DO NOT MODIFY ON VULTR WEBSITE
     * <p>Set script last modification date</p>
     * @param modified last modification date
     * @see Date
     */
    public void setModified(Date modified) {
        this.modified = modified;
    }

    /**
     * Get Vultr script id
     * @return script id
     */
    public int getId() {
        return id;
    }

    /**
     * Get script creation date
     * @return creation date
     * @see Date
     */
    public Date getCreated() {
        return created;
    }

    /**
     * Get last modified date
     * @return last modified date
     * @see Date
     */
    public Date getModified() {
        return modified;
    }

    /**
     * Get script name
     * @return script name
     */
    public String getName() {
        return name;
    }

    /**
     * Get script type
     * @return script type
     * @see JVultrScript.Type
     */
    public Type getType() {
        return type;
    }

    /**
     * Get script content
     * @return script content
     */
    public String getScript() {
        return script;
    }

    @Override
    public String toString() {
        return Reflection.toString(this);
    }
}
