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
 * Created by david on 30/10/15.
 */
public class JVultrServer {
    public enum Status{
        PENDING,
        ACTIVE;
    }
    public enum PowerStatus{
        STOPPED,
        RUNNING;
    }
    public enum ServerState{
        LOCKED,
        NONE,
        OK;
    }
    private int id;
    private String os;
    private String ram;
    private String disk;
    private String mainIp;
    private int vcpus;
    private JVultrRegion region;
    private String defaultPassword;
    private Date created;
    private float pendingCharges;
    private Status status;
    private float costPerMonth;
    private float currentBandwidth;
    private float allowedBandwidth;
    private String netmask;
    private String gateway;
    private PowerStatus powerStatus;
    private ServerState serverState;
    private JVultrPlan plan;
    private JVultrIPV6Network v6Network;
    private JVultrIPV6Network[] v6Networks;
    private String label;
    private String internalIp;
    private String kvmUrl;
    private boolean autoBackups;
    private String tag;

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
            this.created = JVultrAPI.dateFormat.parse(value.get("date_created").getAsString());
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

    public int getId() {
        return id;
    }

    public String getOs() {
        return os;
    }

    public String getRam() {
        return ram;
    }

    public String getDisk() {
        return disk;
    }

    public String getMainIp() {
        return mainIp;
    }

    public int getVcpusCount() {
        return vcpus;
    }

    public JVultrRegion getRegion() {
        return region;
    }

    public String getDefaultPassword() {
        return defaultPassword;
    }

    public Date getCreated() {
        return created;
    }

    public float getPendingCharges() {
        return pendingCharges;
    }

    public Status getStatus() {
        return status;
    }

    public float getCostPerMonth() {
        return costPerMonth;
    }

    public float getCurrentBandwidth() {
        return currentBandwidth;
    }

    public float getAllowedBandwidth() {
        return allowedBandwidth;
    }

    public String getNetmask() {
        return netmask;
    }

    public String getGateway() {
        return gateway;
    }

    public PowerStatus getPowerStatus() {
        return powerStatus;
    }

    public ServerState getServerState() {
        return serverState;
    }

    public JVultrPlan getPlan() {
        return plan;
    }

    public JVultrIPV6Network getV6Network() {
        return v6Network;
    }

    public JVultrIPV6Network[] getV6Networks() {
        return v6Networks;
    }

    public String getLabel() {
        return label;
    }

    public String getInternalIp() {
        return internalIp;
    }

    public String getKvmUrl() {
        return kvmUrl;
    }

    public boolean isAutoBackups() {
        return autoBackups;
    }

    public String getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return Reflection.toString(this);
    }
}
