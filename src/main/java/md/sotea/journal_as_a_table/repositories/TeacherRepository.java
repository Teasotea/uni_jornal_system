package md.sotea.journal_as_a_table.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import md.sotea.journal_as_a_table.entities.Teacher;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("SELECT t FROM teachers t WHERE concat(first_name, ' ', last_name, ' ', email) LIKE %?1%")
    public List<Teacher> getAllBySearch(String q);

}
