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
 * Represent a Vultr OS
 */
public class JVultrOS {
    public enum Arch{
        x64,
        i386;
    }

    private int id;
    private String name;
    private Arch arch;
    private String family;
    private boolean windows;

    public JVultrOS(JsonObject value) {
        this.id = value.get("OSID").getAsInt();
        this.name = value.get("name").getAsString();
        this.arch = Arch.valueOf(value.get("arch").getAsString());
        this.family = value.get("family").getAsString();
        this.windows = value.get("windows").getAsBoolean();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Arch getArch() {
        return arch;
    }

    public String getFamily() {
        return family;
    }

    public boolean isWindows() {
        return windows;
    }

    @Override
    public String toString() {
        return Reflection.toString(this);
    }
}
