package md.sotea.journal_as_a_table.entities;

import javax.persistence.*;

@Entity(name = "students")
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long student_id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private StudentGroup studentGroup;

    public Student() {
    }

    public Student(String firstName, String lastName, StudentGroup studentGroup) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentGroup = studentGroup;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public StudentGroup getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(StudentGroup StudentGroup) {
        this.studentGroup = StudentGroup;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }
}
