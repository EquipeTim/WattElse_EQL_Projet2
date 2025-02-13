package fr.eql.ai116.proj2.tim.entity.dto;

public class PlugDto {

    private long modelId;
    private long IdChargingStation;
    private String plugType;


    /// Constructors////
    public PlugDto() {
    }

    /// Getters///

    public long getModelId() {
        return modelId;
    }

    public long getIdChargingStation() {
        return IdChargingStation;
    }

    public String getPlugType() {
        return plugType;
    }

    /// Setter///

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

    public void setIdChargingStation(long idChargingStation) {
        IdChargingStation = idChargingStation;
    }

    public void setPlugType(String plugType) {
        this.plugType = plugType;
    }
}
