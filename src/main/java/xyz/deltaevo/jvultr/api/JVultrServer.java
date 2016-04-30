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

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import xyz.deltaevo.jvultr.JVultrAPI;
import xyz.deltaevo.jvultr.JVultrCache;
import xyz.deltaevo.jvultr.utils.Reflection;

import java.text.ParseException;
import java.util.Date;

/**
 * Represent a Vultr Server
 * @author DeltaEvolution
 */
public class JVultrServer {

    /**
     * Represent a Vultr Server Status
     * @author DeltaEvolution
     */
    public enum Status{
        PENDING,
        ACTIVE;
    }

    /**
     * Represent a Vultr Server Power Status
     * @author DeltaEvolution
     */
    public enum PowerStatus{
        STOPPED,
        RUNNING;
    }

    /**
     * Represent a Vultr Server State
     * @author DeltaEvolution
     */
    public enum ServerState{
        LOCKED,
        NONE,
        OK;
    }

    /**
     * Vultr Server id
     */
    private int id;

    /**
     * Server os
     */
    private String os;

    /**
     * Server ram amount
     */
    private String ram;

    /**
     * Server disk space
     */
    private String disk;

    /**
     * Server main ip
     */
    private String mainIp;

    /**
     * Server vcpu amount
     */
    private int vcpus;

    /**
     * Server region
     */
    private JVultrRegion region;

    /**
     * Server default password
     */
    private String defaultPassword;

    /**
     * Server creation date
     */
    private Date created;

    /**
     * Server pending charges
     */
    private float pendingCharges;

    /**
     * Server status
     */
    private Status status;

    /**
     * Server cost per month
     */
    private float costPerMonth;

    /**
     * Server current bandwidth
     */
    private float currentBandwidth;

    /**
     * Server allowed bandwidth
     */
    private float allowedBandwidth;

    /**
     * Server netmask
     */
    private String netmask;

    /**
     * Server gateway
     */
    private String gateway;

    /**
     * Server power status
     */
    private PowerStatus powerStatus;

    /**
     * Server state
     */
    private ServerState serverState;

    /**
     * Server plan
     */
    private JVultrPlan plan;

    /**
     * Server IPv6 network
     */
    private JVultrIPV6Network v6Network;

    /**
     * Server IPv6 networks
     */
    private JVultrIPV6Network[] v6Networks;

    /**
     * Server label
     */
    private String label;

    /**
     * Server internal network ip
     */
    private String internalIp;

    /**
     * Server kvm url
     */
    private String kvmUrl;

    /**
     * Server have auto backups
     */
    private boolean autoBackups;

    /**
     * Server tag
     */
    private String tag;

    /**
     * DON'T USE THIS CONSTRUCTOR !
     * @param value the JsonObject representing this object
     */
    public JVultrServer(JsonObject value) {
        this.id = value.get("SUBID").getAsInt();
        this.os = value.get("os").getAsString();
        this.ram = value.get("ram").getAsString();
        this.disk = value.get("disk").getAsString();
        this.mainIp = value.get("main_ip").getAsString();
        this.vcpus = value.get("vcpu_count").getAsInt();
        this.region = JVultrCache.getCachedRegion(value.get("DCID").getAsInt());
        this.defaultPassword = value.get("default_password").getAsString();
        try {
            this.created = JVultrAPI.DATE_FORMAT.parse(value.get("date_created").getAsString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.pendingCharges = value.get("pending_charges").getAsFloat();
        this.status = Status.valueOf(value.get("status").getAsString().toUpperCase());
        this.costPerMonth = value.get("cost_per_month").getAsFloat();
        this.currentBandwidth = value.get("current_bandwidth_gb").getAsFloat();
        this.allowedBandwidth = value.get("allowed_bandwidth_gb").getAsFloat();
        this.netmask = value.get("netmask_v4").getAsString();
        this.gateway = value.get("gateway_v4").getAsString();
        this.powerStatus = PowerStatus.valueOf(value.get("power_status").getAsString().toUpperCase());
        this.serverState = ServerState.valueOf(value.get("server_state").getAsString().toUpperCase());
        this.plan = JVultrCache.getCachedPlan(value.get("VPSPLANID").getAsInt());
        this.v6Network = new JVultrIPV6Network(value);
        JsonArray array = value.get("v6_networks").getAsJsonArray();
        this.v6Networks = new JVultrIPV6Network[array.size()];
        int i = 0;
        for(JsonElement element : array){
            if(element.isJsonObject()){
                v6Networks[i] = new JVultrIPV6Network((JsonObject) element);
                i++;
            }
        }
        this.label = value.get("label").getAsString();
        this.internalIp = value.get("internal_ip").getAsString();
        this.kvmUrl = value.get("kvm_url").getAsString();
        this.autoBackups = value.get("auto_backups").getAsBoolean();
        this.tag = value.get("tag").getAsString();
    }

    /**
     * Get Vultr Server id
     * @return server id
     */
    public int getId() {
        return id;
    }

    /**
     * Get server os
     * @return server os
     */
    public String getOs() {
        return os;
    }

    /**
     * Get server ram amount
     * @return server ram amount
     */
    public String getRam() {
        return ram;
    }

    /**
     * Get server disk space
     * @return disk space
     */
    public String getDisk() {
        return disk;
    }

    /**
     * Get server main ip
     * @return server main ip
     */
    public String getMainIp() {
        return mainIp;
    }

    /**
     * Get vcpu count
     * @return vcpu count
     */
    public int getVcpusCount() {
        return vcpus;
    }

    /**
     * Get server region
     * @return server region
     */
    public JVultrRegion getRegion() {
        return region;
    }

    /**
     * Get server default password
     * @return default password
     */
    public String getDefaultPassword() {
        return defaultPassword;
    }

    /**
     * Get server creation date
     * @return server creation date
     */
    public Date getCreated() {
        return created;
    }

    /**
     * Get server pending charges
     * @return server pending charges
     */
    public float getPendingCharges() {
        return pendingCharges;
    }

    /**
     * Get server status
     * @return server status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Get server cost per month
     * @return server cost per month
     */
    public float getCostPerMonth() {
        return costPerMonth;
    }

    /**
     * Get current server bandwidth
     * @return current server bandwidth
     */
    public float getCurrentBandwidth() {
        return currentBandwidth;
    }

    /**
     * Get server allowed bandwidth
     * @return server allowed bandwidth
     */
    public float getAllowedBandwidth() {
        return allowedBandwidth;
    }

    /**
     * Get server netmask
     * @return server netmask
     */
    public String getNetmask() {
        return netmask;
    }

    /**
     * Get server gateway
     * @return server gateway
     */
    public String getGateway() {
        return gateway;
    }

    /**
     * Get server power status
     * @return server power status
     */
    public PowerStatus getPowerStatus() {
        return powerStatus;
    }

    /**
     * Get server state
     * @return server state
     */
    public ServerState getServerState() {
        return serverState;
    }

    /**
     * Get server plan
     * @return server plan
     */
    public JVultrPlan getPlan() {
        return plan;
    }

    /**
     * Get server main v6 network
     * @return server main v6 network
     */
    public JVultrIPV6Network getV6Network() {
        return v6Network;
    }

    /**
     * Get server v6 networks
     * @return server v6 networks
     */
    public JVultrIPV6Network[] getV6Networks() {
        return v6Networks;
    }

    /**
     * Get server label
     * @return server label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Get server internal network ip
     * @return server internal network ip
     */
    public String getInternalIp() {
        return internalIp;
    }

    /**
     * Get server kvm url
     * @return server kvm url
     */
    public String getKvmUrl() {
        return kvmUrl;
    }

    /**
     * Get server has auto backup
     * @return server has auto backup
     */
    public boolean hasAutoBackups() {
        return autoBackups;
    }

    /**
     * Get server tag
     * @return server tag
     */
    public String getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return Reflection.toString(this);
    }
}
