package fr.delta.jvultr;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.delta.jvultr.api.JVultrOS;
import fr.delta.jvultr.api.JVultrPlan;
import fr.delta.jvultr.api.JVultrRegion;
import fr.delta.jvultr.exception.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by david on 29/10/15.
 */
public class JVultrAPI {
    public static final String endpoint = "https://api.vultr.com/";
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

    public static HashMap<Integer,JVultrRegion> getRegions() throws JVultrException {
        JsonParser parser = new JsonParser();
        JsonElement reponse = parser.parse(get(JVultrAPI.endpoint + "v1/regions/list"));
        if(reponse.isJsonObject()){
            HashMap<Integer , JVultrRegion> regions = new HashMap<>();
            for(Map.Entry<String , JsonElement> element : ((JsonObject)reponse).entrySet()){
                if(element.getValue().isJsonObject())
                    regions.put(Integer.parseInt(element.getKey()) , new JVultrRegion((JsonObject)element.getValue()));
            }
            return regions;
        }
        return new HashMap<>();
    }

    public static HashMap<Integer , JVultrOS> getOSs() throws JVultrException{
        JsonParser parser = new JsonParser();
        JsonElement reponse = parser.parse(get(JVultrAPI.endpoint + "v1/os/list"));
        if(reponse.isJsonObject()){
            HashMap<Integer , JVultrOS> os = new HashMap<>();
            for(Map.Entry<String , JsonElement> element : ((JsonObject)reponse).entrySet()){
                if(element.getValue().isJsonObject())
                    os.put(Integer.parseInt(element.getKey()) , new JVultrOS((JsonObject)element.getValue()));
            }
            return os;
        }
        return new HashMap<>();
    }

    public static HashMap<Integer , JVultrPlan> getPlans() throws JVultrException{
        JsonParser parser = new JsonParser();
        JsonElement reponse = parser.parse(get(JVultrAPI.endpoint + "v1/plans/list"));
        if(reponse.isJsonObject()){
            HashMap<Integer , JVultrPlan> os = new HashMap<>();
            for(Map.Entry<String , JsonElement> element : ((JsonObject)reponse).entrySet()){
                if(element.getValue().isJsonObject())
                    os.put(Integer.parseInt(element.getKey()) , new JVultrPlan((JsonObject)element.getValue()));
            }
            return os;
        }
        return new HashMap<>();
    }

    public static List<JVultrPlan> getPlansFor(int regionId) throws JVultrException{
        JsonParser parser = new JsonParser();
        JsonElement reponse = parser.parse(get(JVultrAPI.endpoint + "v1/regions/availability?DCID=" +regionId));
        if(reponse.isJsonArray()){
            List<JVultrPlan> availablePlans = new ArrayList<>();
            for(JsonElement element : reponse.getAsJsonArray()){
                int planId = element.getAsInt();
                if(!JVultrCache.getCachedPlans().containsKey(planId)) JVultrCache.reloadCachedPlans();
                availablePlans.add(JVultrCache.getCachedPlans().get(planId));
            }
            return availablePlans;
        }
        return new ArrayList<>();
    }

    public static List<JVultrPlan> getPlansFor(JVultrRegion region) throws JVultrException{
        return getPlansFor(region.getId());
    }


    static String get(String url) throws JVultrException{
        try{
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            if(conn.getResponseCode() != 200)
                switch (conn.getResponseCode()){
                    case 400 : throw new InvalidAPILocation(url);
                    case 403 : throw new InvalidAPIKey();
                    case 405 : throw new InvalidHTTPMethod("GET" , url);
                    case 412 : throw new RequestFailed();
                    case 500 : throw new InternalServerError();
                    case 503 : throw new RateLimitExceeded();
                }
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder reponse = new StringBuilder();
            String line;
            while((line = br.readLine()) != null)reponse.append(line);
            br.close();
            return reponse.toString();
        }catch (IOException ex){
            ex.printStackTrace();
            throw new RequestFailed(ex);
        }
    }

    static String post(String url , String parameters) throws JVultrException{
        try{
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            if(parameters != null)conn.getOutputStream().write(parameters.getBytes());
            if(conn.getResponseCode() != 200)
                switch (conn.getResponseCode()){
                    case 400 : throw new InvalidAPILocation(url);
                    case 403 : throw new InvalidAPIKey();
                    case 405 : throw new InvalidHTTPMethod("POST" , url);
                    case 412 : throw new RequestFailed();
                    case 500 : throw new InternalServerError();
                    case 503 : throw new RateLimitExceeded();
                }
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder reponse = new StringBuilder();
            String line;
            while((line = br.readLine()) != null)reponse.append(line);
            br.close();
            return reponse.toString();
        }catch (IOException ex){
            ex.printStackTrace();
            throw new RequestFailed(ex);
        }
    }

    public static JVultrClient newClient(String apiKey){
        return new JVultrClient(apiKey);
    }

    public static void main(String[] args) {
        try {
            JVultrClient client = newClient("");
            System.out.println(JVultrAPI.getPlans());
        } catch (JVultrException e) {
            e.printStackTrace();
        }
    }
}
