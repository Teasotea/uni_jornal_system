package md.sotea.journal_as_a_table.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import md.sotea.journal_as_a_table.entities.Student;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT st FROM students st WHERE concat(first_name, ' ', last_name) LIKE %?1%")
    public List<Student> getAllBySearch(String q);

}
