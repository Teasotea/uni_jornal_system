package md.sotea.journal_as_a_table.service;

import org.springframework.data.domain.Page;

import md.sotea.journal_as_a_table.entities.Grade;
// import md.sotea.journal_as_a_table.entities.Student;

import java.util.List;

public interface GradeService {

    List<Grade> getAllBySearch(String q);

    // java.util.Map<Student, List<Grade>> getAll();

    List<Grade> getAll();

    Grade getOne(Long id);

    Grade saveNew(Grade grade);

    Grade update(Grade grade, Long id);

    void delete(Long id);

    Page<Grade> findPage(int pageNumber);
}
