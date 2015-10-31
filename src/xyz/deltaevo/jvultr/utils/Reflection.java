package xyz.deltaevo.jvultr.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Created by david on 30/10/15.
 */
public class Reflection {

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
