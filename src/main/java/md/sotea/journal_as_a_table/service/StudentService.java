package md.sotea.journal_as_a_table.service;

import java.util.List;

import md.sotea.journal_as_a_table.entities.Student;

public interface StudentService {

    List<Student> getAllBySearch(String q);

    List<Student> getAll();

    Student getOne(Long id);

    Student saveNew(Student student);

    Student update(Student student, Long id);

    void delete(Long id);
}
