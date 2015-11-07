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
 * Represent a Vultr IPV6 Network
 * @author DeltaEvolution
 */
public class JVultrIPV6Network {

    /**
     *IPv6 network
     */
    private String network;

    /**
     *IPv6 ip
     */
    private String ip;

    /**
     *Ipv6 size
     */
    private int size;

    /**
     * DON'T USE THIS CONSTRUCTOR !
     * @param value the JsonObject representing this object
     */
    public JVultrIPV6Network(JsonObject value){
        if(value.has("v6_network") &&!value.get("v6_network").getAsString().isEmpty())
            this.network = value.get("v6_network").getAsString();
        if(value.has("v6_network_ip") && !value.get("v6_network_ip").getAsString().isEmpty())
            this.ip = value.get("v6_main_ip").getAsString();
        if(value.has("v6_network_size") &&!value.get("v6_network_size").getAsString().isEmpty())
            this.size = value.get("v6_network_size").getAsInt();
    }

    /**
     * Get IPv6 Network
     * @return IPv6 Network
     */
    public String getNetwork() {
        return network;
    }

    /**
     * Get IPv6 network ip address
     * @return IPv6 network ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * Get IPv6 network size
     * @return IPv6 network size
     */
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return Reflection.toString(this);
    }
}
