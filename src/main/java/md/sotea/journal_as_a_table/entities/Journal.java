package md.sotea.journal_as_a_table.entities;

import javax.persistence.*;

@Entity(name = "journal")
@Table(name = "journal")
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long row_id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private StudentGroup studentGroup;

    @Column(name = "week", nullable = false)
    private Integer week;

    @Column(name = "Total", nullable = false)
    private Integer total;

    @Column(name = "P1", nullable = false)
    private Integer P1;

    @Column(name = "final_grade", nullable = false)
    private Integer final_grade;

    @Column(name = "final_mark", nullable = false)
    private String final_mark;

    public Journal() {
    }

    public Journal(String name, StudentGroup studentGroup, Integer week, Integer total, Integer P1,
            Integer final_grade,
            String final_mark) {
        this.name = name;
        this.studentGroup = studentGroup;
        this.week = week;
        this.total = total;
        this.P1 = P1;
        this.final_grade = final_grade;
        this.final_mark = final_mark;
    }

    public Long getRow_id() {
        return row_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StudentGroup getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week_number) {
        this.week = week_number;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total_mark) {
        this.total = total_mark;
    }

    public Integer getP1() {
        return P1;
    }

    public void setP1(Integer P1) {
        this.P1 = P1;
    }

    public Integer getFinal_grade() {
        return final_grade;
    }

    public void setFinal_grade(Integer final_grade) {
        this.final_grade = final_grade;
    }

    public String getFinal_mark() {
        return final_mark;
    }

    public void setFinal_mark(String final_mark) {
        this.final_mark = final_mark;
    }
}