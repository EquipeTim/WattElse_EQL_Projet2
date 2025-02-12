package fr.eql.ai116.proj2.tim.entity;
// Avis
public class Evaluation {

    private long idEvaluation;
    private final int rating;

    //Constructor///


    public Evaluation(int rating, long idEvaluation) {
        this.rating = rating;
        this.idEvaluation = idEvaluation;
    }

    /// Getter////

    public long getIdEvaluation() {
        return idEvaluation;
    }

    public int getRating() {
        return rating;
    }
}
