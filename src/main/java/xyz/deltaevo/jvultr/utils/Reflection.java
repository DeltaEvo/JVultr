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

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Utils for Reflection
 * @author DeltaEvolution
 */
public class Reflection {

    /**
     * Print all field of an object
     * @param o the object
     * @return string with all field
     */
    public static String toString(Object o){
        StringBuilder sb = new StringBuilder();
        Class<?> clazz = o.getClass();
        for(Field f : clazz.getDeclaredFields()){
            sb.append(f.getName());
            sb.append(':');
            try {
                f.setAccessible(true);
                if(f.get(o) != null &&f.get(o).getClass().isArray())sb.append(Arrays.toString((Object[]) f.get(o)));
                else sb.append(f.get(o));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            sb.append(',');
        }
        sb.deleteCharAt(sb.length() -1);
        return sb.toString();
    }
}
