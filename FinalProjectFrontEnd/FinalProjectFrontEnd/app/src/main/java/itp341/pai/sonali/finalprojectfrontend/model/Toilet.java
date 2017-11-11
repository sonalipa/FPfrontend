package itp341.pai.sonali.finalprojectfrontend.model;

import android.media.Image;

import java.util.ArrayList;

/**
 * Created by Sonali Pai on 11/8/2017.
 */

public class Toilet {

    //data members
    private long bathroomId;
    private String name;
    private String description;
    private String address;
    private boolean hasDisabilityAccomodations;

    public Toilet(String nameOfLocation, String description, String address, boolean hadDisabilityAccomodations) {
        this.name = nameOfLocation;
        this.description = description;
        this.address = address;
        this.hasDisabilityAccomodations = hadDisabilityAccomodations;
    }

    public String getNameOfLocation() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean getDisabilityAccomodations() {
        return hasDisabilityAccomodations;
    }

    public void setNameOfLocation(String nameOfLocation) {
        this.name = nameOfLocation;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDisabilityAccomodations(boolean disabilityAccomodations) {
        this.hasDisabilityAccomodations = disabilityAccomodations;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return address;
    }
}

