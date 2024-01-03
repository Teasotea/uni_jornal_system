package md.sotea.journal_as_a_table.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import md.sotea.journal_as_a_table.entities.Activity;
import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query("SELECT a FROM activities a WHERE concat(name, ' ', date) LIKE %?1%")
    public List<Activity> getAllBySearch(String q);
}
