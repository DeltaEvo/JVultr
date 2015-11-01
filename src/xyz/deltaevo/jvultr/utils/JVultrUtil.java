package xyz.deltaevo.jvultr.utils;

import xyz.deltaevo.jvultr.JVultrAPI;
import xyz.deltaevo.jvultr.JVultrCache;
import xyz.deltaevo.jvultr.api.JVultrPlan;
import xyz.deltaevo.jvultr.api.JVultrRegion;
import xyz.deltaevo.jvultr.exception.JVultrException;

/**
 * Created by david on 01/11/15.
 */
public class JVultrUtil {

    private JVultrPlan searchPlan(String regionName , int memory) throws JVultrException{
        for(JVultrPlan plan : JVultrAPI.getPlans().values()){
            if(plan.getRam() == memory){
                for(JVultrRegion region : plan.getAvailableRegions()){
                    if(region.getName().equals(regionName)){
                        return plan;
                    }
                }
            }
        }
        return null;
    }

    private JVultrPlan searchCachedPlan(String regionName , int memory) throws JVultrException{
        for(JVultrPlan plan : JVultrCache.getCachedPlans().values()){
            if(plan.getRam() == memory){
                for(JVultrRegion region : plan.getAvailableRegions()){
                    if(region.getName().equals(regionName)){
                        return plan;
                    }
                }
            }
        }
        return null;
    }
}
