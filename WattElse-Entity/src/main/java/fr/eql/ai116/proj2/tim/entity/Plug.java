package fr.eql.ai116.proj2.tim.entity;

import java.io.Serializable;

public class Plug implements Serializable {
        private long idModelCar;
        private long IdChargingStation;
        private String plugType;

    /// Constructors////
    public Plug(long idModelCar, long idChargingStation, String plugType) {
        this.idModelCar = idModelCar;
        IdChargingStation = idChargingStation;
        this.plugType = plugType;
    }


      /// Getters///

        public long getIdModelCar() {
            return idModelCar;
        }

        public long getIdChargingStation() {
            return IdChargingStation;
        }

        public String getPlugType() {
            return plugType;
        }

        /// Setter///

        public void setIdModelCar(long idModelCar) {
            this.idModelCar = idModelCar;
        }

        public void setIdChargingStation(long idChargingStation) {
            IdChargingStation = idChargingStation;
        }

        public void setPlugType(String plugType) {this.plugType = plugType;}
}
