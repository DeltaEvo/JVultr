package xyz.deltaevo.jvultr.api;

import com.google.gson.JsonObject;
import xyz.deltaevo.jvultr.utils.Reflection;

/**
 * Created by david on 30/10/15.
 */
public class JVultrIPV6Network {
    private String network;
    private String ip;
    private int size;
    public JVultrIPV6Network(JsonObject value){
        if(value.has("v6_network") &&!value.get("v6_network").getAsString().isEmpty())
            this.network = value.get("v6_network").getAsString();
        if(value.has("v6_network_ip") && !value.get("v6_network_ip").getAsString().isEmpty())
            this.ip = value.get("v6_main_ip").getAsString();
        if(value.has("v6_network_size") &&!value.get("v6_network_size").getAsString().isEmpty())
            this.size = value.get("v6_network_size").getAsInt();
    }

    public String getNetwork() {
        return network;
    }

    public String getIp() {
        return ip;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return Reflection.toString(this);
    }
}
