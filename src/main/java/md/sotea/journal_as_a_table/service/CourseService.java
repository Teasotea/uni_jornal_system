package md.sotea.journal_as_a_table.service;

import java.util.List;

import md.sotea.journal_as_a_table.entities.Course;

public interface CourseService {

    List<Course> getAllBySearch(String q);

    List<Course> getAll();

    Course getOne(Long id);

    Course saveNew(Course course);

    Course update(Course course, Long id);

    void delete(Long id);
}
