package Entities;

import java.io.Serializable;

public class Score implements Serializable {
    private int id;
    private int userId;
    private int exerciseId;
    private int score;

    public Score(){}
    public Score(int id, int userId, int exerciseId, int score) {
        this.id = id;
        this.userId = userId;
        this.exerciseId = exerciseId;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", userId=" + userId +
                ", exerciseId=" + exerciseId +
                ", score=" + score +
                '}';
    }
}
