package xyz.deltaevo.jvultr;

import xyz.deltaevo.jvultr.api.JVultrISO;
import xyz.deltaevo.jvultr.api.JVultrOS;
import xyz.deltaevo.jvultr.api.JVultrPlan;
import xyz.deltaevo.jvultr.api.JVultrRegion;
import xyz.deltaevo.jvultr.exception.JVultrException;

import java.util.HashMap;

/**
 * Created by david on 30/10/15.
 */
public class JVultrCache {
    //////////////////////////////////////////////////////////
    //                       Static Cache                  //
    ////////////////////////////////////////////////////////
    private static HashMap<Integer,JVultrRegion> cachedRegions;
    private static HashMap<Integer , JVultrPlan> cachedPlans;
    private static JVultrOS custom;
    static {
        try {
            cachedRegions = JVultrAPI.getRegions();
        } catch (JVultrException e) {
            e.printStackTrace();
        }
        try {
            cachedPlans = JVultrAPI.getPlans();
        } catch (JVultrException e) {
            e.printStackTrace();
        }
        try {
            for(JVultrOS os : JVultrAPI.getOSs().values()){
                if(os.getName().equals("Custom")){
                    custom = os;break;
                }
            }
        } catch (JVultrException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<Integer,JVultrRegion> getCachedRegions(){
        return cachedRegions;
    }

    public static void setCachedRegions(HashMap<Integer, JVultrRegion> cachedRegions) {
        cachedRegions = cachedRegions;
    }

    public static void reloadCachedRegions(){
        try {
            cachedRegions = JVultrAPI.getRegions();
        } catch (JVultrException e) {
            e.printStackTrace();
        }
    }

    public static JVultrRegion getCachedRegion(int id){
        if(!getCachedRegions().containsKey(id))reloadCachedRegions();
        return getCachedRegions().get(id);
    }

    public static HashMap<Integer,JVultrPlan> getCachedPlans(){
        return cachedPlans;
    }

    public static void setCachedPlans(HashMap<Integer, JVultrPlan> cachedPlans) {
        cachedPlans = cachedPlans;
    }

    public static void reloadCachedPlans(){
        try {
            cachedPlans = JVultrAPI.getPlans();
        } catch (JVultrException e) {
            e.printStackTrace();
        }
    }

    public static JVultrPlan getCachedPlan(int id){
        if(!getCachedPlans().containsKey(id))reloadCachedPlans();
        return getCachedPlans().get(id);
    }

    public static JVultrOS getCustom() {
        return custom;
    }
}
