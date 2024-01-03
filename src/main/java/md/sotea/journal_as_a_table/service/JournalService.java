package md.sotea.journal_as_a_table.service;

import java.util.List;

import md.sotea.journal_as_a_table.entities.Journal;

public interface JournalService {

    List<Journal> getAllBySearch(String q);

    List<Journal> getAll();

    Journal getOne(Long id);

    Journal saveNew(Journal journal);

    Journal update(Journal journal, Long id);

    void delete(Long id);

}