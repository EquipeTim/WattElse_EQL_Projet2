package fr.eql.ai116.proj2.tim.entity;

import java.io.Serializable;
import java.util.List;

public class Plug implements Serializable {



        private long idPlugType;
        private PlugType plugType;

    /// Constructors////
    public Plug(long idPlugType, PlugType plugType) {
        this.idPlugType = idPlugType;
        this.plugType = plugType;
    }



    /// Getters///

        public long getIdPlugType() {
        return idPlugType;
    }
        public PlugType getPlugType() {
            return plugType;
        }



    /// Setter///

    public void setIdPlugType(long idPlugType) {
        this.idPlugType = idPlugType;
    }

        public void setPlugType(PlugType plugType) {
            this.plugType = plugType;
        }



}


