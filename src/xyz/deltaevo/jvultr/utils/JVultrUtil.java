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
package xyz.deltaevo.jvultr.utils;

import xyz.deltaevo.jvultr.JVultrAPI;
import xyz.deltaevo.jvultr.JVultrCache;
import xyz.deltaevo.jvultr.api.JVultrPlan;
import xyz.deltaevo.jvultr.api.JVultrRegion;
import xyz.deltaevo.jvultr.exception.JVultrException;

/**
 * @author DeltaEvolution
 * Utils for JVultr
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
