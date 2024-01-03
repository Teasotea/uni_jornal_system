package md.sotea.journal_as_a_table.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import md.sotea.journal_as_a_table.entities.StudentGroup;
import java.util.List;

public interface StudentGroupRepository extends JpaRepository<StudentGroup, Long> {

    @Query("SELECT sg FROM studentGroup sg WHERE sg.groupName LIKE %?1%")
    public List<StudentGroup> getAllBySearch(String q);
}