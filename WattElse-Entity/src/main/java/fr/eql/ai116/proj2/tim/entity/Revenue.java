package fr.eql.ai116.proj2.tim.entity;

import java.io.Serializable;

public class Revenue implements Serializable {
    private Long terminalId;
    private Float revenue;

    public Revenue(Long terminalId, Float revenue) {
        this.terminalId = terminalId;
        this.revenue = revenue;
    }

    public Long getTerminalId() {
        return terminalId;
    }

    public Float getRevenue() {
        return revenue;
    }
}

