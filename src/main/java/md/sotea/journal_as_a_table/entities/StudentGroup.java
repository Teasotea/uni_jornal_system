package md.sotea.journal_as_a_table.entities;

import javax.persistence.*;

@Entity(name = "studentGroup")
@Table(name = "studentGroup")
public class StudentGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long group_id;

    @Column(name = "group_name", nullable = false)
    private String groupName;

    public StudentGroup() {
    }

    public StudentGroup(String groupName) {
        this.groupName = groupName;
    }

    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long id) {
        this.group_id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String name) {
        this.groupName = name;
    }
}
