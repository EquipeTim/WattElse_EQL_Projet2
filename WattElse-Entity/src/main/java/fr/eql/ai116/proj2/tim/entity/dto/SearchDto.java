package fr.eql.ai116.proj2.tim.entity.dto;

import fr.eql.ai116.proj2.tim.entity.PlugType;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SearchDto implements Serializable {
    private Long stationId;
    private Long userId;
    private Integer searchRadius;
    private Float startingLat;
    private Float startingLong;
    private PlugType plugType;
    private Integer plugId;

    private String date;
    private String time;
    private String timeZone;


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

    public void setDate(String date) {
        this.date = date;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPlugId(Integer plugId) {
        this.plugId = plugId;
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

    public String getDate() {
        return date;
    }

    public Integer getPlugId() {
        return plugId;
    }

    public String getWeekDay() {
        DateTimeFormatter dtfInput = DateTimeFormatter.ofPattern("u-M-d", Locale.ENGLISH);
        DateTimeFormatter dtfOutput = DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH);
        return LocalDate.parse(date, dtfInput).format(dtfOutput);
    }

    public String getTimeZone() {
        return timeZone;
    }

    public Long getStationId() {
        return stationId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getTime() {
        return time;
    }
}
