package md.sotea.journal_as_a_table.entities;

import javax.persistence.*;

@Entity(name = "grades")
@Table(name = "grades")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long grade_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;

    private int grade;

    public Grade() {
    }

    public Grade(Student student, Activity activity, int grade) {
        this.student = student;
        this.activity = activity;
        this.grade = grade;
    }

    public Long getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(Long grade_id) {
        this.grade_id = grade_id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity_id) {
        this.activity = activity_id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
