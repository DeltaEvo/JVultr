package xyz.deltaevo.jvultr;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import xyz.deltaevo.jvultr.api.*;
import xyz.deltaevo.jvultr.exception.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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
        JsonElement response = new JsonParser().parse(get(JVultrAPI.endpoint + "v1/regions/list"));
        if(response.isJsonObject()){
            HashMap<Integer , JVultrRegion> regions = new HashMap<>();
            for(Map.Entry<String , JsonElement> element : ((JsonObject)response).entrySet()){
                if(element.getValue().isJsonObject())
                    regions.put(Integer.parseInt(element.getKey()) , new JVultrRegion((JsonObject)element.getValue()));
            }
            return regions;
        }
        return new HashMap<>();
    }

    public static HashMap<Integer , JVultrOS> getOSs() throws JVultrException{
        JsonElement response = new JsonParser().parse(get(JVultrAPI.endpoint + "v1/os/list"));
        if(response.isJsonObject()){
            HashMap<Integer , JVultrOS> os = new HashMap<>();
            for(Map.Entry<String , JsonElement> element : ((JsonObject)response).entrySet()){
                if(element.getValue().isJsonObject())
                    os.put(Integer.parseInt(element.getKey()) , new JVultrOS((JsonObject)element.getValue()));
            }
            return os;
        }
        return new HashMap<>();
    }

    public static HashMap<Integer , JVultrApplication> getApplications() throws JVultrException{
        JsonElement response = new JsonParser().parse(get(JVultrAPI.endpoint + "v1/app/list"));
        if(response.isJsonObject()){
            HashMap<Integer , JVultrApplication> applications = new HashMap<>();
            for(Map.Entry<String , JsonElement> element : ((JsonObject)response).entrySet()){
                if(element.getValue().isJsonObject())
                    applications.put(Integer.parseInt(element.getKey()) , new JVultrApplication((JsonObject)element.getValue()));
            }
            return applications;
        }
        return new HashMap<>();
    }

    public static HashMap<Integer , JVultrPlan> getPlans() throws JVultrException{
        JsonElement response = new JsonParser().parse(get(JVultrAPI.endpoint + "v1/plans/list"));
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

    public static List<JVultrPlan> getPlansFor(int regionId) throws JVultrException{
        JsonElement response = new JsonParser().parse(get(JVultrAPI.endpoint + "v1/regions/availability?DCID=" +regionId));
        if(response.isJsonArray()){
            List<JVultrPlan> availablePlans = new ArrayList<>();
            for(JsonElement element : response.getAsJsonArray()){
                availablePlans.add(JVultrCache.getCachedPlan(element.getAsInt()));
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
            StringBuilder response = new StringBuilder();
            String line;
            while((line = br.readLine()) != null)response.append(line);
            br.close();
            return response.toString();
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
            if(parameters != null){
                conn.setRequestProperty( "charset", "utf-8");
                conn.setRequestProperty("Content-Length",Integer.toString(parameters.length()));
                conn.getOutputStream().write(parameters.getBytes("UTF-8"));
            }
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
            StringBuilder response = new StringBuilder();
            String line;
            while((line = br.readLine()) != null)response.append(line);
            br.close();
            return response.toString();
        }catch (IOException ex){
            ex.printStackTrace();
            throw new RequestFailed(ex);
        }
    }

    static String post(String url , Map<String , Object> parameters) throws JVultrException{
        try{
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String,Object> param : parameters.entrySet()) {
                if (sb.length() != 0) sb.append('&');
                sb.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                sb.append('=');
                sb.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            return post(url , sb.toString());
        }catch (IOException e){
            throw new RequestFailed(e);
        }
    }

    public static JVultrClient newClient(String apiKey){
        return new JVultrClient(apiKey);
    }
}
