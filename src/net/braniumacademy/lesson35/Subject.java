package net.braniumacademy.lesson35;

public class Subject {
    private String id;
    private String name;
    private int credit;
    private int numOfLesson;
    private int numOfExam;

    public Subject() {
    }

    public Subject(String id) {
        this.id = id;
    }

    public Subject(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Subject(String id, String name, int credit, int numOfLesson, int numOfExam) {
        this.id = id;
        this.name = name;
        this.credit = credit;
        this.numOfLesson = numOfLesson;
        this.numOfExam = numOfExam;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getNumOfLesson() {
        return numOfLesson;
    }

    public void setNumOfLesson(int numOfLesson) {
        this.numOfLesson = numOfLesson;
    }

    public int getNumOfExam() {
        return numOfExam;
    }

    public void setNumOfExam(int numOfExam) {
        this.numOfExam = numOfExam;
    }
}
