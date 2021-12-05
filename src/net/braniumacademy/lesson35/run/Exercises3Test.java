package net.braniumacademy.lesson35.run;

import net.braniumacademy.lesson35.Course;
import net.braniumacademy.lesson35.Student;
import net.braniumacademy.lesson35.Subject;
import net.braniumacademy.lesson35.Transcript;

import java.util.ArrayList;
import java.util.Scanner;

public class Exercises3Test {
    public static void main(String[] args) {
        int choice = 0;
        ArrayList<Subject> subjects = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();
        createFakeSubjects(subjects);
        createFakeStudents(students);
        createFakeCourses(courses, subjects);
        var input = new Scanner(System.in);
        do {
            System.out.println("======================= MENU ============================");
            System.out.println("=   1. Thêm mới môn học vào danh sách môn học.          =");
            System.out.println("=   2. Thêm mới sinh viên vào danh sách sinh viên.      =");
            System.out.println("=   3. Thêm mới lớp học vào danh sách lớp học.          =");
            System.out.println("=   4. Thêm mới sinh viên vào lớp học nào đó.           =");
            System.out.println("=   5. Hiển thị danh sách môn học.                      =");
            System.out.println("=   6. Hiển thị danh sách sinh viên.                    =");
            System.out.println("=   7. Hiển thị danh sách lớp học.                      =");
            System.out.println("=   8. Nhập và tính điểm trung bình.                    =");
            System.out.println("=   9. Xét học lực cho các sinh viên trong lớp.         =");
            System.out.println("=   10. Tìm sinh viên theo mã trong lớp học nào đó.     =");
            System.out.println("=   11. Hiển thị bảng điểm sinh viên trong lớp nào đó.  =");
            System.out.println("=   0. Thoát chương trình.                              =");
            System.out.println("=========================================================");
            System.out.println("Xin mời chọn: ");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 0:
                    System.out.println("======= CHƯƠNG TRÌNH KẾT THÚC =======");
                    break;
                case 1:
                    var s = createNewSubject(input);
                    subjects.add(s);
                    System.out.println("==> Thêm môn học thành công <==");
                    break;
                case 2:
                    var student = createNewStudent(input);
                    students.add(student);
                    System.out.println("==> Thêm mới sinh viên thành công <==");
                    break;
                case 3:
                    if (subjects.size() > 0) {
                        var course = createNewCourse(input, subjects);
                        if (course != null) {
                            courses.add(course);
                            System.out.println("==> Thêm lớp học thành công <==");
                        } else {
                            System.out.println("==> Thêm lớp học thất bại <==");
                        }
                    } else {
                        System.out.println("==> Danh sách môn học rỗng <==");
                    }
                    break;
                case 4:
                    if (courses.size() > 0 && students.size() > 0) {
                        var isSucess = addStudentToCourse(courses, students, input);
                        if (isSucess) {
                            System.out.println("==> Thêm sinh viên vào lớp học thành công! <==");
                        } else {
                            System.out.println("==> Thêm sinh viên vào lớp học thất bại! <==");
                        }
                    } else {
                        System.out.println("==> Danh sách lớp học hoặc danh sách sinh viên rỗng. <==");
                    }
                    break;
                case 5:
                    showSubjects(subjects);
                    break;
                case 6:
                    showStudents(students);
                    break;
                case 7:
                    showCourses(courses);
                    break;
                case 8:
                    if (courses.size() > 0) {
                        addGrades(courses, input);
                    } else {
                        System.out.println("==> Danh sách lớp học rỗng <==");
                    }
                    break;
                case 9:
                    if (courses.size() > 0) {
                        calculCapacity(courses);
                    } else {
                        System.out.println("==> Danh sách lớp học rỗng <==");
                    }
                    break;
                case 10:
                    if (courses.size() > 0) {
                        searchStudentInTheCourse(courses, students, input);
                    } else {
                        System.out.println("==> Danh sách lớp học rỗng <==");
                    }
                    break;
                case 11:
                    if (courses.size() > 0) {
                        System.out.println("Nhập mã lớp cần hiển thị thông tin bảng điểm: ");
                        var courseId = input.nextLine();
                        var couse = findCourse(courses, courseId);
                        if (couse != null) {
                            showTranscripts(couse);
                        } else {
                            System.out.println("==> Không tìm thấy lớp học theo yêu cầu. <==");
                        }
                    } else {
                        System.out.println("==> Danh sách lớp học rỗng <==");
                    }
                    break;
                default:
                    System.out.println("=== Sai chức năng. Vui lòng chọn lại! ===");
                    break;
            }
        } while (choice != 0);
    }

    private static void showTranscripts(Course couse) {
        // mã lớp, mã sinh viên, họ tên sinh viên, điểm bài ktra 1, 2, 3, điểm TB, học lực
        System.out.printf("%-10s%-12s%-20s%-10s%-10s%-10s%-10s%-12s\n",
                "Mã lớp", "Mã SV", "Họ tên", "Điểm H1", "Điểm H2",
                "Điểm H3", "Điểm TB", "Học lực");
        for (var tos : couse.getTranscriptOfStudents()) {
            System.out.printf("%-10s%-12s%-20s%-10.2f%-10.2f%-10.2f%-10.2f%-12s\n",
                    couse.getId(), tos.getStudent().getId(), tos.getStudent().getFullName(),
                    tos.getTranscript().getGrade1(), tos.getTranscript().getGrade2(),
                    tos.getTranscript().getGrade3(), tos.getTranscript().getGpa(),
                    tos.getTranscript().getCapacity().getValue());
        }
    }

    private static void searchStudentInTheCourse(
            ArrayList<Course> courses, ArrayList<Student> students, Scanner input) {
        System.out.println("Nhập mã sinh viên cần tìm: ");
        var studentId = input.nextLine();
        System.out.println("Nhập mã lớp cần tìm: ");
        var courseId = input.nextLine();
        var course = findCourse(courses, studentId, courseId);
        if (course != null) {
            System.out.println("==> Tìm thấy sinh viên mã \"" + studentId + "\"! <==");
            for (var student : students) {
                if (student.getId().compareTo(studentId) == 0) {
                    showStudent(student);
                    break;
                }
            }
        } else {
            System.out.println("==> Không tìm thấy sinh viên này <==");
        }
    }

    private static Course findCourse(ArrayList<Course> courses, String courseId) {
        for (int i = 0; i < courses.size(); i++) {
            var course = courses.get(i);
            if (course.getId().compareTo(courseId) == 0) {
                return course;
            }
        }
        return null;
    }

    private static Course findCourse(ArrayList<Course> courses, String studentId, String courseId) {
        for (int i = 0; i < courses.size(); i++) {
            var course = courses.get(i);
            if (course.getId().compareTo(courseId) == 0) {
                for (int j = 0; j < course.getTranscriptOfStudents().size(); j++) {
                    var tos = course.getTranscriptOfStudents().get(j);
                    if (tos.getStudent().getId().compareTo(studentId) == 0) {
                        return course;
                    }
                }
            }
        }
        return null;
    }

    private static void createFakeCourses(ArrayList<Course> courses, ArrayList<Subject> subjects) {
        courses.add(new Course("C001", "Java OOP 01", "A2-101", "14h00-16h00", subjects.get(0)));
        courses.add(new Course("C002", "Java OOP 02", "A2-201", "14h00-18h00", subjects.get(0)));
        courses.add(new Course("C001", "Java OOP 03", "A2-301", "14h00-16h00", subjects.get(0)));
        courses.add(new Course("C001", "Java OOP 04", "A3-401", "8h00-12h00", subjects.get(0)));
    }

    // String id, String address, String fullName, String email,
    //                   String gender, String className, String major, String phoneNumber
    private static void createFakeStudents(ArrayList<Student> students) {
        students.add(new Student("SV001", "Ha Noi", "Tran Van Nam",
                "nam@xmail.com", "Nam", "CN1", "CNTT", "0977458654"));
        students.add(new Student("SV002", "Ha Noi", "Tran Van Hung",
                "hung@xmail.com", "Nam", "CN2", "CNTT", "0912321456"));
        students.add(new Student("SV003", "Ha Noi", "Le Van Tam",
                "tam@xmail.com", "Nam", "CN1", "CNTT", "0912354785"));
        students.add(new Student("SV004", "Ha Noi", "Hoang Thanh Trung",
                "trung@xmail.com", "Nam", "CN3", "CNTT", "0987123123"));
        students.add(new Student("SV005", "Ha Noi", "Truong Van Khanh",
                "khanh@xmail.com", "Nam", "CN1", "CNTT", "0934125478"));
        students.add(new Student("SV006", "Ha Noi", "Nguyen Thuy Trang",
                "trang@xmail.com", "Nu", "CN1", "CNTT", "035864123"));
        students.add(new Student("SV007", "Ha Noi", "Ngo Thi Lan",
                "lan@xmail.com", "Nu", "CN2", "CNTT", "032145698"));
        students.add(new Student("SV008", "Ha Noi", "Doan Thanh Huong",
                "huong@xmail.com", "Nu", "CN3", "CNTT", "0358123654"));
    }

    private static void createFakeSubjects(ArrayList<Subject> subjects) {
        subjects.add(new Subject("SJ001", "Java OOP", 4, 40, 3));
        subjects.add(new Subject("SJ002", "Java Web", 4, 42, 3));
        subjects.add(new Subject("SJ003", "C++", 3, 32, 3));
        subjects.add(new Subject("SJ004", "Python", 4, 40, 3));
        subjects.add(new Subject("SJ004", "C#", 3, 32, 3));
    }

    private static void calculCapacity(ArrayList<Course> courses) {
        System.out.println("Nhập mã lớp học cần xét học lực: ");
        var input = new Scanner(System.in);
        var id = input.nextLine();
        var course = findCourseById(courses, id);
        if (course != null) {
            if (course.getTranscriptOfStudents().size() > 0) {
                boolean isSucess = true;
                for (int i = 0; i < course.getTranscriptOfStudents().size(); i++) {
                    if (course.getTranscriptOfStudents().get(i).getTranscript() != null) {
                        course.getTranscriptOfStudents().get(i).getTranscript().calculCapacity();
                    } else {
                        System.out.println("==> Chưa nhập bảng điểm <==");
                        isSucess = false;
                        break;
                    }
                }
                if (isSucess) {
                    System.out.println("==> Xét học lực thành công <==");
                } else {
                    System.out.println("==> Xét học lực thất bại <==");
                }
            } else {
                System.out.println("==> Lớp học này chưa có sinh viên nào <==");
            }
        } else {
            System.out.println("==> Lớp học cần tìm không tồn tại <==");
        }
    }

    private static void addGrades(ArrayList<Course> courses, Scanner input) {
        System.out.println("Nhập mã lớp học: ");
        var courseId = input.nextLine();
        var course = findCourseById(courses, courseId);
        if (course != null) {
            showStudentInCourse(course.getTranscriptOfStudents());
            System.out.println("Nhập mã sinh viên: ");
            var studentId = input.nextLine();
            var isFound = false;
            for (var s : course.getTranscriptOfStudents()) {
                if (s.getStudent().getId().compareTo(studentId) == 0) {
                    isFound = true;
                    break;
                }
            }
            if (isFound) {
                var tran = createTranscript();
                course.setTranscriptOfStudents(new Student(studentId), tran);
                System.out.println("==> Cập nhật bảng điểm thành công <==");
            } else {
                System.out.println("==> Không tồn tại sinh viên có mã vừa nhập trong danh sách lớp <==");
            }
        } else {
            System.out.println("==> Không tồn tại lớp học cần tìm <==");
        }
    }

    private static Transcript createTranscript() {
        Transcript transcript = new Transcript();
        var input = new Scanner(System.in);
        System.out.println("Nhập điểm hệ số 1: ");
        var g1 = input.nextFloat();
        System.out.println("Nhập điểm hệ số 2: ");
        var g2 = input.nextFloat();
        System.out.println("Nhập điểm hệ số 3: ");
        var g3 = input.nextFloat();

        transcript.setGrade1(g1);
        transcript.setGrade2(g2);
        transcript.setGrade3(g3);
        transcript.calculGpa();

        return transcript;
    }

    private static boolean addStudentToCourse(ArrayList<Course> courses,
                                              ArrayList<Student> students, Scanner input) {
        System.out.println("Nhập mã lớp học: ");
        var courseId = input.nextLine();
        var course = findCourseById(courses, courseId);
        if (course != null) {
            showStudentInCourse(course.getTranscriptOfStudents());
            System.out.println("Nhập mã sinh viên: ");
            var studentId = input.nextLine();
            var student = findStudentById(students, studentId);
            if (student != null) {
                course.addStudentToCourse(student);
                return true;
            } else {
                System.out.println("==> Sinh viên cần tìm không tồn tại <==");
                return false;
            }
        } else {
            System.out.println("==> Lớp cần tìm không tồn tại <==");
            return false;
        }
    }

    private static Student findStudentById(ArrayList<Student> students, String studentId) {
        for (int i = 0; i < students.size(); i++) {
            if (studentId.compareTo(students.get(i).getId()) == 0) {
                return students.get(i);
            }
        }
        return null;
    }

    private static void showStudentInCourse(ArrayList<Course.TranscriptOfStudent> transcriptOfStudents) {
        System.out.println("==> Các sinh viên đã có trong lớp học: <==");
        System.out.printf("%-12s%-22s%-15s%-20s%-12s%-15s%-15s%-15s\n",
                "Mã SV", "Họ và tên", "Địa chỉ", "Email", "Giới tính", "Số ĐT",
                "Lớp", "Khoa");
        for (var item : transcriptOfStudents) {
            showStudent(item.getStudent());
        }
    }

    private static Course findCourseById(ArrayList<Course> courses, String courseId) {
        for (int i = 0; i < courses.size(); i++) {
            if (courseId.compareTo(courses.get(i).getId()) == 0) {
                return courses.get(i);
            }
        }
        return null;
    }

    private static void showCourses(ArrayList<Course> courses) {
        System.out.println("==========================> " +
                "Danh sách các lớp học <==========================");
        System.out.printf("%-12s%-25s%-15s%-15s%-15s\n",
                "Mã lớp", "Tên lớp", "Phòng học", "Giờ học", "Môn học");
        for (var course : courses) {
            showCourse(course);
        }
    }

    private static void showCourse(Course course) {
        System.out.printf("%-12s%-25s%-15s%-15s%-15s\n",
                course.getId(), course.getName(), course.getClassRoom(),
                course.getTime(), course.getSubject().getName());
    }

    private static Course createNewCourse(Scanner input, ArrayList<Subject> subjects) {
        System.out.println("Nhập mã lớp học: ");
        var id = input.nextLine();
        System.out.println("Nhập tên lớp học: ");
        var name = input.nextLine();
        System.out.println("Nhập tên phòng học: ");
        var room = input.nextLine();
        System.out.println("Nhập giờ học: ");
        var time = input.nextLine();
        System.out.println("Nhập mã môn học: ");
        var subjectId = input.nextLine();
        var subject = findSubjectById(subjects, subjectId);
        if (subject == null) {
            System.out.println("==> Không tồn tại môn học có mã vừa nhập <==");
            return null;
        } else {
            return new Course(id, name, room, time, subject);
        }
    }

    private static Subject findSubjectById(ArrayList<Subject> subjects, String subjectId) {
        for (var s : subjects) {
            if (s.getId().compareTo(subjectId) == 0) {
                return s;
            }
        }
        return null;
    }

    private static void showStudents(ArrayList<Student> students) {
        System.out.println("===============================================> " +
                "Danh sách sinh viên <===============================================");
        System.out.printf("%-12s%-22s%-15s%-20s%-12s%-15s%-15s%-15s\n",
                "Mã SV", "Họ và tên", "Địa chỉ", "Email", "Giới tính", "Số ĐT",
                "Lớp", "Khoa");
        for (var student : students) {
            showStudent(student);
        }
    }

    private static void showStudent(Student student) {
        System.out.printf("%-12s%-22s%-15s%-20s%-12s%-15s%-15s%-15s\n",
                student.getId(), student.getFullName(), student.getAddress(),
                student.getEmail(), student.getGender(), student.getPhoneNumber(),
                student.getClassName(), student.getMajor());
    }

    private static Student createNewStudent(Scanner input) {
        System.out.println("Nhập mã sinh viên: ");
        var id = input.nextLine();
        System.out.println("Nhập họ và tên: ");
        var fullName = input.nextLine();
        System.out.println("Nhập địa chỉ: ");
        var address = input.nextLine();
        System.out.println("Nhập email: ");
        var email = input.nextLine();
        System.out.println("Nhập số điện thoại: ");
        var phoneNumber = input.nextLine();
        System.out.println("Nhập giới tính: ");
        var gender = input.nextLine();
        System.out.println("Nhập lớp: ");
        var className = input.nextLine();
        System.out.println("Nhập khoa: ");
        var major = input.nextLine();
        return new Student(id, address, fullName,
                email, gender, className, major, phoneNumber);
    }

    private static void showSubjects(ArrayList<Subject> subjects) {
        System.out.println("=========================> " +
                "DANH SÁCH MÔN HỌC <=========================");
        System.out.printf("%-12s%-25s%-12s%-12s%-12s\n",
                "Mã môn", "Tên môn", "Số tín", "Số tiết", "Số bài KT");
        for (var s : subjects) {
            showSubject(s);
        }
    }

    private static void showSubject(Subject s) {
        System.out.printf("%-12s%-25s%-12s%-12s%-12s\n",
                s.getId(), s.getName(), s.getCredit(),
                s.getNumOfLesson(), s.getNumOfExam());
    }

    private static Subject createNewSubject(Scanner input) {
        System.out.println("Mã môn học: ");
        var id = input.nextLine();
        System.out.println("Tên môn học: ");
        var name = input.nextLine();
        System.out.println("Số tín chỉ: ");
        var credit = input.nextInt();
        System.out.println("Số tiết học: ");
        var lesson = input.nextInt();
        System.out.println("Số bài kiểm tra: ");
        var numOfExam = input.nextInt();
        return new Subject(id, name, credit, lesson, numOfExam);
    }
}
