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
 * Represent a Vultr Dns
 */
public class JVultrDns {

    private String domain;
    private Date created;

    public JVultrDns(JsonObject value){
        this.domain = value.get("domain").getAsString();
        try {
            this.created = JVultrAPI.dateFormat.parse(value.get("date_created").getAsString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public JVultrDns(String domain , Date created){

    }

    public String getDomain() {
        return domain;
    }

    public Date getCreated() {
        return created;
    }

    @Override
    public String toString() {
        return Reflection.toString(this);
    }
}
