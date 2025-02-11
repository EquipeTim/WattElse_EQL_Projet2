package fr.eql.ai116.proj2.tim.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Session implements Serializable {
    private final long id;
    private final String token;
    private final Timestamp timestamp;
    private final long userId;

    public Session(long id, String token, Timestamp timestamp, long userId) {
        this.id = id;
        this.token = token;
        this.timestamp = timestamp;
        this.userId = userId;
    }
    /// /////// ///
    /// Getters ///
    /// ////// ///
    public long getId() {
        return id;
    }
    public String getToken() {
        return token;
    }
    public Timestamp getTimestamp() {
        return timestamp;
    }
    public long getUserId() {
        return userId;
    }
}
