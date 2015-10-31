package xyz.deltaevo.jvultr;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import xyz.deltaevo.jvultr.api.*;
import xyz.deltaevo.jvultr.exception.JVultrException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by david on 29/10/15.
 */
public class JVultrClient {

    private String apiKey;

    JVultrClient(String apiKey){
        this.apiKey = apiKey;
    }


    public JVultrAccountInfo getAccountInfo() throws JVultrException{
        JsonParser parser = new JsonParser();
        JsonElement response = parser.parse(JVultrAPI.get(JVultrAPI.endpoint + "v1/account/info?api_key=" + apiKey));
        if(response.isJsonObject())return new JVultrAccountInfo((JsonObject) response);
        else return null;
    }
    public HashMap<String , JVultrSnapshot> getSnapshots() throws JVultrException {
        JsonParser parser = new JsonParser();
        JsonElement response = parser.parse(JVultrAPI.get(JVultrAPI.endpoint + "v1/snapshot/list?api_key=" + apiKey));
        if(response.isJsonObject()){
            HashMap<String , JVultrSnapshot> snapshots = new HashMap<>();
            for(Map.Entry<String , JsonElement> element : ((JsonObject)response).entrySet()){
                if(element.getValue().isJsonObject())
                    snapshots.put(element.getKey() , new JVultrSnapshot((JsonObject)element.getValue()));
            }
            return snapshots;
        }
        return new HashMap<>();
    }

    public HashMap<Integer , JVultrISO> getISOs() throws JVultrException {
        JsonParser parser = new JsonParser();
        JsonElement response = parser.parse(JVultrAPI.get(JVultrAPI.endpoint + "v1/iso/list?api_key=" + apiKey));
        if(response.isJsonObject()){
            HashMap<Integer , JVultrISO> isos = new HashMap<>();
            for(Map.Entry<String , JsonElement> element : ((JsonObject)response).entrySet()){
                if(element.getValue().isJsonObject())
                    isos.put(Integer.parseInt(element.getKey()) , new JVultrISO((JsonObject)element.getValue()));
            }
            return isos;
        }
        return new HashMap<>();
    }

    public HashMap<String , JVultrOS> getOsChangeListFor(JVultrServer server) throws JVultrException {
        JsonParser parser = new JsonParser();
        JsonElement response = parser.parse(JVultrAPI.get(JVultrAPI.endpoint + "v1/server/os_change_list?api_key=" + apiKey + "&SUBID=" + server.getId()));
        if(response.isJsonObject()){
            HashMap<String , JVultrOS> oss = new HashMap<>();
            for(Map.Entry<String , JsonElement> element : ((JsonObject)response).entrySet()){
                if(element.getValue().isJsonObject())
                    oss.put(element.getKey() , new JVultrOS((JsonObject)element.getValue()));
            }
            return oss;
        }
        return new HashMap<>();
    }

    public HashMap<Integer , JVultrPlan> getPlans() throws JVultrException{
        JsonElement response = new JsonParser().parse(JVultrAPI.get(JVultrAPI.endpoint + "v1/plans/list?api_key=" + apiKey));
        if(response.isJsonObject()){
            HashMap<Integer , JVultrPlan> os = new HashMap<>();
            for(Map.Entry<String , JsonElement> element : ((JsonObject)response).entrySet()){
                if(element.getValue().isJsonObject())
                    os.put(Integer.parseInt(element.getKey()) , new JVultrPlan((JsonObject)element.getValue()));
            }
            return os;
        }
        return new HashMap<>();
    }

    public HashMap<Integer , JVultrServer> getSevers() throws JVultrException {
        JsonElement response = new JsonParser().parse(JVultrAPI.get(JVultrAPI.endpoint + "v1/server/list?api_key=" + apiKey));
        if(response.isJsonObject()){
            HashMap<Integer , JVultrServer> snapshots = new HashMap<>();
            for(Map.Entry<String , JsonElement> element : ((JsonObject)response).entrySet()){
                if(element.getValue().isJsonObject())
                    snapshots.put(Integer.parseInt(element.getKey()) , new JVultrServer((JsonObject)element.getValue()));
            }
            return snapshots;
        }
        return new HashMap<>();
    }

    public List<JVultrDns> getDNSs() throws JVultrException{
        JsonElement response = new JsonParser().parse(JVultrAPI.get(JVultrAPI.endpoint + "v1/dns/list?api_key=" + apiKey));
        if(response.isJsonArray()){
            List<JVultrDns> dnss = new ArrayList<>();
            for(JsonElement element : response.getAsJsonArray()){
                if(element.isJsonObject())dnss.add(new JVultrDns((JsonObject) element));
            }
            return dnss;
        }
        return new ArrayList<>();
    }

    public List<JVultrDnsRecord> getDNSRecords(String domain) throws JVultrException{
        JsonElement response = new JsonParser().parse(JVultrAPI.get(JVultrAPI.endpoint + "v1/dns/records?api_key=" + apiKey + "&domain=" + domain));
        if(response.isJsonArray()){
            List<JVultrDnsRecord> records = new ArrayList<>();
            for(JsonElement element : response.getAsJsonArray()){
                if(element.isJsonObject())records.add(new JVultrDnsRecord((JsonObject) element));
            }
            return records;
        }
        return new ArrayList<>();
    }

    public List<JVultrDnsRecord> getDNSRecords(JVultrDns dns) throws JVultrException{
        return getDNSRecords(dns.getDomain());
    }

    public void destroySnapshot(String id) throws JVultrException{
        HashMap<String , Object> params = new HashMap<>();
        params.put("SNAPSHOTID" , id);
        JVultrAPI.post(JVultrAPI.endpoint + "v1/snapshot/destroy?api_key=" + apiKey , params);
    }

    public void createSnapshot(int id) throws JVultrException{
        HashMap<String , Object> params = new HashMap<>();
        params.put("SUBID" , id);
        System.out.println(JVultrAPI.post(JVultrAPI.endpoint + "v1/snapshot/create?api_key=" + apiKey, params));
    }

    public void destroySnapshot(JVultrSnapshot snapshot) throws JVultrException{
        destroySnapshot(snapshot.getId());
    }

    public void createSnapshot(JVultrServer server) throws JVultrException{
        createSnapshot(server.getId());
    }

    public JVultrServer createServer(int locationId , int planId , int osId ,
                             String ipxeChainUrl , Integer isoId , Integer scriptId , String snapshotId ,
                             Boolean enableIpv6 , Boolean enablePrivateNetwork , String label ,
                             Integer sshKeyId , Boolean autoBackups , Integer appId , String userData ,
                             Boolean notifyActivate , Boolean ddosProtection) throws JVultrException{
        HashMap<String , Object> params = new HashMap<>();
        params.put("DCID" , locationId);
        params.put("VPSPLANID" , planId);
        params.put("OSID" , osId);
        if(ipxeChainUrl != null)params.put("ipxe_chain_url" , ipxeChainUrl);
        if(isoId != null)params.put("ISOID",isoId);
        if(scriptId != null)params.put("SCRIPTID",scriptId);
        if(snapshotId != null)params.put("SNAPSHOTID",snapshotId);
        if(enableIpv6 != null)params.put("enable_ipv6",enableIpv6 ? "yes" : "no");
        if(enablePrivateNetwork != null)params.put("enable_private_network",enablePrivateNetwork ? "yes" : "no");
        if(label != null)params.put("label",label);
        if(sshKeyId != null)params.put("SSHKEYID",sshKeyId);
        if(autoBackups != null)params.put("auto_backups" ,autoBackups? "yes" : "no");
        if(appId != null)params.put("APPID",appId);
        if(userData!= null)params.put("userdata",userData);
        if(notifyActivate != null)params.put("notify_activate",notifyActivate ? "yes" : "no");
        if(ddosProtection != null)params.put("ddos_protection",ddosProtection? "yes" : "no");
        JsonElement response = new JsonParser().parse(JVultrAPI.post(JVultrAPI.endpoint + "v1/server/create?api_key=" + apiKey, params));
        if(response.isJsonObject()){
            return getSevers().get(((JsonObject)response).get("SUBID").getAsInt());
        }else return null;
    }

    public JVultrServer createServer(int locationId , int planId , int osId) throws JVultrException{
        return createServer(locationId, planId, osId , null , null , null , null , null , null
                , null , null , null , null , null , null ,null);
    }

    public void destroyServer(int id) throws JVultrException {
        HashMap<String , Object> params = new HashMap<>();
        params.put("SUBID" , id);
        JVultrAPI.post(JVultrAPI.endpoint + "v1/server/destroy?api_key=" + apiKey , params);
    }

    public void destroyServer(JVultrServer server) throws JVultrException{
        destroyServer(server.getId());
    }


}
