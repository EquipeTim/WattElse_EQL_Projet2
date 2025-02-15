package fr.eql.ai116.proj2.tim.entity.dto;

import fr.eql.ai116.proj2.tim.entity.PlugType;

import java.io.Serializable;

public class SearchDto implements Serializable {
    private Integer searchRadius;
    private Float startingLat;
    private Float startingLong;
    private PlugType plugType;


    public void setSearchRadius(Integer searchRadius) {
        this.searchRadius = searchRadius;
    }

    public void setStartingLat(Float startingLat) {
        this.startingLat = startingLat;
    }

    public void setStartingLong(Float startingLong) {
        this.startingLong = startingLong;
    }

    public void setPlugType(String plug) {
        this.plugType = PlugType.valueOf(plug);
    }

    /// Getters///

    public Integer getSearchRadius() {
        return searchRadius;
    }

    public Float getStartingLat() {
        return startingLat;
    }

    public Float getStartingLong() {
        return startingLong;
    }

    public PlugType getPlugType() {
        return plugType;
    }


}
