package fr.delta.jvultr;

import fr.delta.jvultr.api.JVultrPlan;
import fr.delta.jvultr.api.JVultrRegion;
import fr.delta.jvultr.exception.JVultrException;

import java.util.HashMap;

/**
 * Created by david on 30/10/15.
 */
public class JVultrCache {
    private static HashMap<Integer,JVultrRegion> cachedRegions;
    private static HashMap<Integer , JVultrPlan> cachedPlans;
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
}
