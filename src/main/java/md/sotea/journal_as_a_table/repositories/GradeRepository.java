package md.sotea.journal_as_a_table.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import md.sotea.journal_as_a_table.entities.Grade;
// import md.sotea.journal_as_a_table.entities.Student;
// import md.sotea.journal_as_a_table.entities.Activity;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    @Query("SELECT g FROM grades g WHERE concat(g.grade, ' ', g.student.firstName, " +
            "' ', g.student.lastName, ' ', g.activity.name) LIKE %?1%")
    public List<Grade> getAllBySearch(String q);

}
