package Entities;

import java.io.Serializable;

public class ExerciseResult implements Serializable {

    private int id;
    private int exerciseId;
    private int userId;
    private String startTime;
    private String endTime;
    private int numberOfCorrectAnswers;
    private int score;

    public ExerciseResult(){}
    public ExerciseResult(int id, int exerciseId, int userId, String startTime, String endTime, int numberOfCorrectAnswers,int score) {
        this.id = id;
        this.exerciseId = exerciseId;
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.numberOfCorrectAnswers = numberOfCorrectAnswers;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getNumberOfCorrectAnswers() {
        return numberOfCorrectAnswers;
    }

    public void setNumberOfCorrectAnswers(int numberOfCorrectAnswers) {
        this.numberOfCorrectAnswers = numberOfCorrectAnswers;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "ExerciseResult{" +
                "id=" + id +
                ", exerciseId=" + exerciseId +
                ", userId=" + userId +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", numberOfCorrectAnswers=" + numberOfCorrectAnswers +
                ", score=" + score +
                '}';
    }
}
