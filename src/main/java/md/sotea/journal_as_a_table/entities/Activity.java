package md.sotea.journal_as_a_table.entities;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity(name = "activities")
@Table(name = "activities")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activity_id;

    private String name;

    @CreationTimestamp
    private Date date;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Activity() {
    }

    public Activity(String name, Date date, Course course) {
        this.name = name;
        this.date = date;
    }

    public Activity(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public Activity(String name) {
        this.name = name;
    }

    public Long getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(Long activity_id) {
        this.activity_id = activity_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course_id) {
        this.course = course_id;
    }
}
