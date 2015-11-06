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
 * Represent a Vultr Region
 */
public class JVultrRegion {

    /**
     * @author DeltaEvolution
     * Represent Earth Continents
     */
    public enum Continent{
        NORTH_AMERICA,
        SOUTH_AMERICA,
        ASIA,
        EUROPE,
        AUSTRALIA,
        AFRICA;
    }
    private int id;
    private String name;
    private String country;
    private Continent continent;
    private String state;
    private boolean ddosProtection;

    /**
     * DON'T USE THIS CONSTRUCTOR !
     * @param value the JsonObject representing this object
     */
    public JVultrRegion(JsonObject value) {
        this.id = value.get("DCID").getAsInt();
        this.name = value.get("name").getAsString();
        this.country = value.get("country").getAsString();
        this.continent = Continent.valueOf(value.get("continent").getAsString().replace(' ' , '_').toUpperCase());
        this.state = value.get("state").getAsString();
        this.ddosProtection = value.get("ddos_protection").getAsBoolean();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public Continent getContinent() {
        return continent;
    }

    public String getState() {
        return state;
    }

    public boolean haveDDOSProtection(){
        return ddosProtection;
    }

    @Override
    public String toString() {
        return Reflection.toString(this);
    }
}
