package Entities;

import java.io.Serializable;

public class ExerciseResultDetail implements Serializable {

    private int id;
    private int userId;
    private int exerciseId;
    private int exerciseResultId;
    private int A;
    private int B;
    private int time;
    private boolean isTrue;
    private int answerValue;

    public ExerciseResultDetail(){}

    public ExerciseResultDetail(int id, int userId, int exerciseId, int exerciseResultId, int a, int b, int time, boolean isTrue, int answerValue) {
        this.id = id;
        this.userId = userId;
        this.exerciseId = exerciseId;
        this.exerciseResultId = exerciseResultId;
        A = a;
        B = b;
        this.time = time;
        this.isTrue = isTrue;
        this.answerValue = answerValue;
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

    public int getExerciseResultId() {
        return exerciseResultId;
    }

    public void setExerciseResultId(int exerciseResultId) {
        this.exerciseResultId = exerciseResultId;
    }

    public int getA() {
        return A;
    }

    public void setA(int a) {
        A = a;
    }

    public int getB() {
        return B;
    }

    public void setB(int b) {
        B = b;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrueFalse(boolean trueFalse) {
        this.isTrue = trueFalse;
    }

    public int getAnswerValue() {
        return answerValue;
    }

    public void setAnswerValue(int answerValue) {
        this.answerValue = answerValue;
    }

    @Override
    public String toString() {
        return "ExerciseResultDetail{" +
                "id=" + id +
                ", userId=" + userId +
                ", exerciseId=" + exerciseId +
                ", exerciseResultId=" + exerciseResultId +
                ", A=" + A +
                ", B=" + B +
                ", time=" + time +
                ", answer=" + isTrue +
                ", answerValue=" + answerValue +
                '}';
    }
}
