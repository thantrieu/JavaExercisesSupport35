package net.braniumacademy.lesson35;

public class Student {
    private String id;
    private String address;
    private FullName fullName;
    private String email;
    private String gender;
    private String className;
    private String major;
    private String phoneNumber;

    public Student() {
        id = "";
        address = "";
        fullName = new FullName();
        email = "";
        address = "";
        gender = "";
        className = "";
        major = "";
        phoneNumber = "";
    }

    public Student(String id) {
        this.id = id;
        fullName = new FullName();
    }

    public Student(String id, String fullName) {
        this.id = id;
        this.fullName = new FullName();
        this.setFullName(fullName);
    }

    public Student(String id, String address, String fullName, String email,
                   String gender, String className, String major, String phoneNumber) {
        this();
        this.id = id;
        this.address = address;
        this.setFullName(fullName);
        this.email = email;
        this.gender = gender;
        this.className = className;
        this.major = major;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullName() {
        return fullName.last + " " + fullName.mid + fullName.first;
    }

    public void setFullName(String fullName) {
        var words = fullName.split(" ");
        this.fullName.first = words[words.length - 1];
        this.fullName.last = words[0];
        this.fullName.mid = "";
        for (int i = 1; i < words.length - 1; i++) {
            this.fullName.mid += words[i] + " ";
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public class FullName {
        private String first;
        private String last;
        private String mid;
    }
}
