package xyz.deltaevo.jvultr.api;

import com.google.gson.JsonObject;
import xyz.deltaevo.jvultr.utils.Reflection;

/**
 * Created by david on 29/10/15.
 */
public class JVultrRegion {

    enum Continent{
        NORTH_AMERICA,
        SOUTH_AMERICA,
        ASIA,
        EUROPE,
        AUSTRALIA,
        AFRICA;
    }
    private int id;
    private String name;
    private String country;
    private Continent continent;
    private String state;
    private boolean ddosProtection;
    public JVultrRegion(JsonObject value) {
        this.id = value.get("DCID").getAsInt();
        this.name = value.get("name").getAsString();
        this.country = value.get("country").getAsString();
        this.continent = Continent.valueOf(value.get("continent").getAsString().replace(' ' , '_').toUpperCase());
        this.state = value.get("state").getAsString();
        this.ddosProtection = value.get("ddos_protection").getAsBoolean();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public Continent getContinent() {
        return continent;
    }

    public String getState() {
        return state;
    }

    public boolean haveDDOSProtection(){
        return ddosProtection;
    }

    @Override
    public String toString() {
        return Reflection.toString(this);
    }
}
