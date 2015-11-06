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
 * @author DeltaEvolution
 * Represent a Vultr Dns Record
 */
public class JVultrDnsRecord {
    public enum Type{
        A,
        MX,
        SRV,
        AAAA,
        CNAME;
    }

    private int id;
    private Type type;
    private String name;
    private String data;
    private int priority;

    public JVultrDnsRecord(JsonObject value){
        this.id = value.get("RECORDID").getAsInt();
        this.type = Type.valueOf(value.get("type").getAsString());
        this.name = value.get("name").getAsString();
        this.data = value.get("data").getAsString();
        this.priority = value.get("priority").getAsInt();
    }
    public int getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return Reflection.toString(this);
    }
}
