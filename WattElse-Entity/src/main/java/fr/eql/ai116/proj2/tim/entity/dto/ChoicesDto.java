package fr.eql.ai116.proj2.tim.entity.dto;

import java.io.Serializable;

public class ChoicesDto implements Serializable {
    private final Long choiceId;
    private final String choice;

    public ChoicesDto(Long choiceId, String choice) {
        this.choiceId = choiceId;
        this.choice = choice;
    }

    public Long getChoiceId() {
        return choiceId;
    }

    public String getChoice() {
        return choice;
    }
}
