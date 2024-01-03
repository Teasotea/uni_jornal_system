package md.sotea.journal_as_a_table.service.Implementation;

import org.springframework.stereotype.Service;

import md.sotea.journal_as_a_table.entities.Teacher;
import md.sotea.journal_as_a_table.exceptions.ResourceNotFoundException;
import md.sotea.journal_as_a_table.repositories.TeacherRepository;
import md.sotea.journal_as_a_table.service.TeacherService;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> getAllBySearch(String q) {
        return teacherRepository.getAllBySearch(q);
    }

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher getOne(Long id) {
        return teacherRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Teacher saveNew(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher update(Teacher teacher, Long id) {
        Teacher targetedTeacher = teacherRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        targetedTeacher.setFirstName(teacher.getFirstName());
        targetedTeacher.setLastName(teacher.getLastName());
        teacherRepository.save(targetedTeacher);
        return targetedTeacher;
    }

    @Override
    public void delete(Long id) {
        teacherRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        teacherRepository.deleteById(id);
    }
}
