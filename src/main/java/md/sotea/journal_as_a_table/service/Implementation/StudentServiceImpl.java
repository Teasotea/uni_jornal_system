package md.sotea.journal_as_a_table.service.Implementation;

// import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import md.sotea.journal_as_a_table.entities.Student;
import md.sotea.journal_as_a_table.exceptions.ResourceNotFoundException;
import md.sotea.journal_as_a_table.repositories.StudentRepository;
import md.sotea.journal_as_a_table.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllBySearch(String q) {
        return studentRepository.getAllBySearch(q);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student getOne(Long id) {
        return studentRepository.findById(id).orElseThrow(
                ResourceNotFoundException::new);
    }

    @Override
    public Student saveNew(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student update(Student student, Long id) {
        Student targetedStudent = studentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        targetedStudent.setFirstName(student.getFirstName());
        targetedStudent.setLastName(student.getLastName());
        targetedStudent.setStudentGroup(student.getStudentGroup());
        studentRepository.save(targetedStudent);
        return targetedStudent;
    }

    @Override
    public void delete(Long id) {
        studentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        studentRepository.deleteById(id);
    }
}
