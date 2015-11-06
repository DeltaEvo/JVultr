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
 * Represent a Vultr Application
 */
public class JVultrApplication {

    /**
     * Application's id
     */
    private int id;

    /**
     * Application name
     */
    private String name;

    /**
     * Application short name
     */
    private String shortName;

    /**
     * Application deploy name
     */
    private String deployName;

    /**
     * DON'T USE THIS CONSTRUCTOR !
     * @param value the JsonObject representing this object
     */
    public JVultrApplication(JsonObject value){
        this.id = value.get("APPID").getAsInt();
        this.name = value.get("name").getAsString();
        this.shortName = value.get("short_name").getAsString();
        this.deployName = value.get("deploy_name").getAsString();
    }

    /**
     * Get application id
     * @return application id
     */
    public int getId() {
        return id;
    }

    /**
     * Get application name
     * @return application name
     */
    public String getName() {
        return name;
    }

    /**
     * Get application short name
     * @return short name
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Get application deploy name
     * @return application deploy name
     */
    public String getDeployName() {
        return deployName;
    }

    @Override
    public String toString() {
        return Reflection.toString(this);
    }
}
