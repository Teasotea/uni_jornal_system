package md.sotea.journal_as_a_table.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import md.sotea.journal_as_a_table.entities.Journal;

import java.util.List;

public interface JournalRepository extends JpaRepository<Journal, Long> {

    @Query("SELECT t FROM journal t WHERE concat(name, ' ', studentGroup) LIKE %?1%")
    public List<Journal> getAllBySearch(String q);

}