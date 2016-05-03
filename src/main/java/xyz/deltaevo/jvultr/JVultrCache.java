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
package xyz.deltaevo.jvultr;

import xyz.deltaevo.jvultr.api.JVultrOS;
import xyz.deltaevo.jvultr.api.JVultrPlan;
import xyz.deltaevo.jvultr.api.JVultrRegion;
import xyz.deltaevo.jvultr.exception.JVultrException;

import java.util.Map;

/**
 * A JVultr Cache to limit requests
 * @author DeltaEvolution
 */
public class JVultrCache {
    //////////////////////////////////////////////////////////
    //                       Static Cache                  //
    ////////////////////////////////////////////////////////
    private static Map<Integer,JVultrRegion> cachedRegions;
    private static Map<Integer , JVultrPlan> cachedPlans;
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

    public static Map<Integer,JVultrRegion> getCachedRegions(){
        return cachedRegions;
    }

    public static void setCachedRegions(Map<Integer, JVultrRegion> regions) {
        cachedRegions = regions;
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

    public static Map<Integer,JVultrPlan> getCachedPlans(){
        return cachedPlans;
    }

    public static void setCachedPlans(Map<Integer, JVultrPlan> plans) {
        cachedPlans = plans;
    }

    public static void reloadCachedPlans(){
        try {
            cachedPlans = JVultrAPI.getPlans();
        } catch (JVultrException e) {
            e.printStackTrace();
        }
    }

    public static JVultrPlan getCachedPlan(int id){
        if(!getCachedPlans().containsKey(id))
            reloadCachedPlans();
        return getCachedPlans().get(id);
    }

    public static JVultrOS getCustom() {
        return custom;
    }
}
