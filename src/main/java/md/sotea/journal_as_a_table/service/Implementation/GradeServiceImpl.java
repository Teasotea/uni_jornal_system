package md.sotea.journal_as_a_table.service.Implementation;

// import org.hibernate.mapping.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import md.sotea.journal_as_a_table.entities.Grade;
// import md.sotea.journal_as_a_table.entities.Student;
import md.sotea.journal_as_a_table.exceptions.ResourceNotFoundException;
import md.sotea.journal_as_a_table.repositories.GradeRepository;
import md.sotea.journal_as_a_table.service.GradeService;

import java.util.List;
// import java.util.stream.Collectors;

@Service
public class GradeServiceImpl implements GradeService {

    public final int NUMBER_OF_ITEMS_PER_PAGE = 1;
    GradeRepository gradeRepository;

    public GradeServiceImpl(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Grade> getAllBySearch(String q) {
        return gradeRepository.getAllBySearch(q);
    }

    public Page<Grade> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, NUMBER_OF_ITEMS_PER_PAGE);
        return gradeRepository.findAll(pageable);
    }

    // @Override
    // public java.util.Map<Student, List<Grade>> getAll() {
    // return
    // gradeRepository.findAll().stream().collect(Collectors.groupingBy(Grade::getStudent));
    // }

    @Override
    public List<Grade> getAll() { // write here code for groupby by student and grades. So that unique student
                                  // will have list of grades
        return gradeRepository.findAll();
    }

    @Override
    public Grade getOne(Long id) {
        return gradeRepository.findById(id).orElseThrow(
                ResourceNotFoundException::new);
    }

    @Override
    public Grade saveNew(Grade grade) {
        return gradeRepository.save(grade);
    }

    @Override
    public Grade update(Grade grade, Long id) {
        Grade targetedGrade = gradeRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        targetedGrade.setActivity(grade.getActivity());
        targetedGrade.setGrade(grade.getGrade());
        targetedGrade.setStudent(grade.getStudent());
        gradeRepository.save(targetedGrade);
        return targetedGrade;
    }

    @Override
    public void delete(Long id) {
        gradeRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        gradeRepository.deleteById(id);
    }
}
