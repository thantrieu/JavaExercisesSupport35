package net.braniumacademy.lesson35;

import java.util.ArrayList;

public class Course {
    private String id;
    private String name;
    private String classRoom;
    private String time;
    private Subject subject;
    private ArrayList<TranscriptOfStudent> transcriptOfStudents;

    public Course() {
        id = "";
        classRoom = "";
        name = "";
        time = "";
        subject = null;
        transcriptOfStudents = new ArrayList<>();
    }

    public Course(String id, String name, String classRoom, String time, Subject subject) {
        transcriptOfStudents = new ArrayList<>();
        this.id = id;
        this.name = name;
        this.classRoom = classRoom;
        this.time = time;
        this.subject = subject;
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

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public ArrayList<TranscriptOfStudent> getTranscriptOfStudents() {
        return transcriptOfStudents;
    }

    public void addStudentToCourse(Student student) {
        transcriptOfStudents.add(new TranscriptOfStudent(student, null));
    }

    public void setTranscriptOfStudents(Student student, Transcript transcript) {
        for (int i = 0; i < transcriptOfStudents.size(); i++) {
            if(transcriptOfStudents.get(i).student.getId().compareTo(student.getId()) == 0) {
                transcriptOfStudents.get(i).transcript = transcript;
                break;
            }
        }
    }

    public class TranscriptOfStudent {
        private Student student;
        private Transcript transcript;

        public TranscriptOfStudent() {

        }

        public TranscriptOfStudent(Student student, Transcript transcript) {
            this.student = student;
            this.transcript = transcript;
        }

        public Student getStudent() {
            return student;
        }

        public void setStudent(Student student) {
            this.student = student;
        }

        public Transcript getTranscript() {
            return transcript;
        }

        public void setTranscript(Transcript transcript) {
            this.transcript = transcript;
        }
    }
}
