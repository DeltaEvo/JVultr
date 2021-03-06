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
package xyz.deltaevo.jvultr.api;

import com.google.gson.JsonObject;
import xyz.deltaevo.jvultr.utils.Reflection;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;

/**
 * Represent a Vultr UserData in Base64
 * @author DeltaEvolution
 */
public class JVultrUserData {

    /**
     * Base64 encoded Vultr user data
     */
    private String userData;

    /**
     * DON'T USE THIS CONSTRUCTOR !
     * @param value the JsonObject representing this object
     */
    public JVultrUserData(JsonObject value){
        userData = value.get("userdata").getAsString();
    }

    /**
     * Get base64 encoded user data
     * @return encoded user data
     */
    public String getUserData() {
        return userData;
    }

    /**
     * Get decoded user data
     * @return decoded user data
     */
    public String decode(){
        try {
            return new String(DatatypeConverter.parseBase64Binary(new String(userData.getBytes("UTF-8"))));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return Reflection.toString(this);
    }
}
