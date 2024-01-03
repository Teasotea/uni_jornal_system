package md.sotea.journal_as_a_table.service.Implementation;

import org.springframework.stereotype.Service;

import md.sotea.journal_as_a_table.entities.StudentGroup;
import md.sotea.journal_as_a_table.exceptions.ResourceNotFoundException;
import md.sotea.journal_as_a_table.repositories.StudentGroupRepository;
import md.sotea.journal_as_a_table.service.StudentGroupService;

import java.util.List;

@Service
public class StudentGroupServiceImpl implements StudentGroupService {

    StudentGroupRepository studentGroupRepository;

    public StudentGroupServiceImpl(StudentGroupRepository studentGroupRepository) {
        this.studentGroupRepository = studentGroupRepository;
    }

    @Override
    public List<StudentGroup> getAllBySearch(String q) {
        return studentGroupRepository.getAllBySearch(q);
    }

    @Override
    public List<StudentGroup> getAll() {
        return studentGroupRepository.findAll();
    }

    @Override
    public StudentGroup getOne(Long id) {
        return studentGroupRepository.findById(id).orElseThrow(
                ResourceNotFoundException::new);
    }

    @Override
    public StudentGroup saveNew(StudentGroup studentGroup) {
        return studentGroupRepository.save(studentGroup);
    }

    @Override
    public StudentGroup update(StudentGroup studentGroup, Long id) {
        StudentGroup targetedStudentGroup = studentGroupRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        targetedStudentGroup.setGroupName(studentGroup.getGroupName());
        studentGroupRepository.save(targetedStudentGroup);
        return targetedStudentGroup;
    }

    @Override
    public void delete(Long id) {
        studentGroupRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        studentGroupRepository.deleteById(id);
    }
}
