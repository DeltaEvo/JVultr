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
 * Represent a Vultr OS
 * @author DeltaEvolution
 */
public class JVultrOS {

    /**
     * Represent a Vultr OS Arch
     * @author DeltaEvolution
     */
    public enum Arch{
        x64,
        i386;
    }

    /**
     * Vultr OS id
     */
    private int id;

    /**
     * OS name
     */
    private String name;

    /**
     * OS arch
     */
    private Arch arch;

    /**
     * OS family
     */
    private String family;

    /**
     * OS is windows ?
     */
    private boolean windows;

    /**
     * DON'T USE THIS CONSTRUCTOR !
     * @param value the JsonObject representing this object
     */
    public JVultrOS(JsonObject value) {
        this.id = value.get("OSID").getAsInt();
        this.name = value.get("name").getAsString();
        this.arch = Arch.valueOf(value.get("arch").getAsString());
        this.family = value.get("family").getAsString();
        this.windows = value.get("windows").getAsBoolean();
    }

    /**
     * Get OS id
     * @return os id
     */
    public int getId() {
        return id;
    }

    /**
     * Get OS name
     * @return os name
     */
    public String getName() {
        return name;
    }

    /**
     * Get OS arch
     * @return os arch
     */
    public Arch getArch() {
        return arch;
    }

    /**
     * Get OS family
     * @return os family
     */
    public String getFamily() {
        return family;
    }

    /**
     * OS is windows ?
     * @return is windows ?
     */
    public boolean isWindows() {
        return windows;
    }

    @Override
    public String toString() {
        return Reflection.toString(this);
    }
}
