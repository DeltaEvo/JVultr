package xyz.deltaevo.jvultr;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import xyz.deltaevo.jvultr.api.JVultrSnapshot;
import xyz.deltaevo.jvultr.exception.JVultrException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by david on 29/10/15.
 */
public class JVultrClient {

    private final String apiKey;

    JVultrClient(String apiKey){
        this.apiKey = apiKey;
    }

    public HashMap<String , JVultrSnapshot> getSnapshot() throws JVultrException {
        JsonParser parser = new JsonParser();
        JsonElement reponse = parser.parse(JVultrAPI.get(JVultrAPI.endpoint + "v1/snapshot/list?api_key=" + apiKey));
        if(reponse.isJsonObject()){
            HashMap<String , JVultrSnapshot> snapshots = new HashMap<>();
            for(Map.Entry<String , JsonElement> element : ((JsonObject)reponse).entrySet()){
                if(element.getValue().isJsonObject())
                    snapshots.put(element.getKey() , new JVultrSnapshot((JsonObject)element.getValue()));
            }
            return snapshots;
        }
        return new HashMap<>();
    }
}
