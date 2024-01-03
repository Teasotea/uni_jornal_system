package md.sotea.journal_as_a_table.service;

import java.util.List;

import md.sotea.journal_as_a_table.entities.Teacher;

public interface TeacherService {

    List<Teacher> getAllBySearch(String q);

    List<Teacher> getAll();

    Teacher getOne(Long id);

    Teacher saveNew(Teacher teacher);

    Teacher update(Teacher teacher, Long id);

    void delete(Long id);
}
