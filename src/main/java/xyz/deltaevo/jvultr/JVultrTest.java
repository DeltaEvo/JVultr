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
 * along with JVultr
 */
package xyz.deltaevo.jvultr;

import xyz.deltaevo.jvultr.api.JVultrISO;
import xyz.deltaevo.jvultr.api.JVultrPlan;
import xyz.deltaevo.jvultr.api.JVultrRegion;
import xyz.deltaevo.jvultr.exception.JVultrException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JVultrTest {
    public static void main(String[] args) throws IOException {
        try {
            JVultrClient client = JVultrAPI.newClient(new BufferedReader(new FileReader("/home/david/Desktop/apiKey.txt")).readLine());
            JVultrISO iso = null;
            for(JVultrISO i : client.getISOs().values()){
                if(i.getFilename().equals("mon.iso")){
                   iso = i;
                    break;
                }
            }
            if(iso == null)return;
            for(JVultrPlan plan : JVultrAPI.getPlans().values()){
                if(plan.getCpus() == 4){
                    for(JVultrRegion region : plan.getAvailableRegions()){
                        if(region.getName().equalsIgnoreCase("paris")){
                            client.createServer(region , plan , JVultrCache.getCustom(), null , iso , null , null
                            ,null , null , "Le serveur de Sceat" , null , null , null ,null , true , null);
                            break;
                        }
                    }
                }
            }
        } catch (JVultrException e) {
            e.printStackTrace();
        }

    }

}
