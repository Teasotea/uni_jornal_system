package md.sotea.journal_as_a_table.service.Implementation;

import org.springframework.stereotype.Service;

import md.sotea.journal_as_a_table.entities.Journal;
import md.sotea.journal_as_a_table.exceptions.ResourceNotFoundException;
import md.sotea.journal_as_a_table.repositories.JournalRepository;
import md.sotea.journal_as_a_table.service.JournalService;

import java.util.List;

@Service
public class JournalServiceImpl implements JournalService {

    JournalRepository journalRepository;

    public JournalServiceImpl(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    @Override
    public List<Journal> getAllBySearch(String q) {
        return journalRepository.getAllBySearch(q);
    }

    @Override
    public List<Journal> getAll() {
        return journalRepository.findAll();
    }

    @Override
    public Journal getOne(Long id) {
        return journalRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Journal saveNew(Journal journal) {
        return journalRepository.save(journal);
    }

    @Override
    public Journal update(Journal journal, Long id) {
        Journal targetedJournal = journalRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        // targetedJournal.setName(journal.getName());
        // targetedJournal.setStudentGroup(journal.getStudentGroup());
        targetedJournal.setWeek(journal.getWeek());
        targetedJournal.setTotal(journal.getTotal());
        targetedJournal.setP1(journal.getP1());
        targetedJournal.setFinal_grade(journal.getFinal_grade());
        targetedJournal.setFinal_mark(journal.getFinal_mark());
        journalRepository.save(targetedJournal);
        return targetedJournal;
    }

    @Override
    public void delete(Long id) {
        journalRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        journalRepository.deleteById(id);
    }

}