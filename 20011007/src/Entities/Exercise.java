package Entities;

import java.io.Serializable;

public class Exercise implements Serializable {
    private int id;
    private int A1;
    private int A2;
    private int B1;
    private int B2;
    private int N;
    private String title;

    public Exercise(){}
    public Exercise(int id, int a1, int a2, int b1, int b2, int n,String title) {
        this.id = id;
        A1 = a1;
        A2 = a2;
        B1 = b1;
        B2 = b2;
        N = n;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getA1() {
        return A1;
    }

    public void setA1(int a1) {
        A1 = a1;
    }

    public int getA2() {
        return A2;
    }

    public void setA2(int a2) {
        A2 = a2;
    }

    public int getB1() {
        return B1;
    }

    public void setB1(int b1) {
        B1 = b1;
    }

    public int getB2() {
        return B2;
    }

    public void setB2(int b2) {
        B2 = b2;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", A1=" + A1 +
                ", A2=" + A2 +
                ", B1=" + B1 +
                ", B2=" + B2 +
                ", N=" + N +
                ", title='" + title + '\'' +
                '}';
    }
}
