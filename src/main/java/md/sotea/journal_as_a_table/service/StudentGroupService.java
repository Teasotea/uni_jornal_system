package md.sotea.journal_as_a_table.service;

import java.util.List;

import md.sotea.journal_as_a_table.entities.StudentGroup;

public interface StudentGroupService {

    List<StudentGroup> getAllBySearch(String q);

    List<StudentGroup> getAll();

    StudentGroup getOne(Long id);

    StudentGroup saveNew(StudentGroup student);

    StudentGroup update(StudentGroup student, Long id);

    void delete(Long id);
}
