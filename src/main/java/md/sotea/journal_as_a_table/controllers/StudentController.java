package md.sotea.journal_as_a_table.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import md.sotea.journal_as_a_table.entities.Student;
import md.sotea.journal_as_a_table.entities.StudentGroup;
import md.sotea.journal_as_a_table.repositories.StudentGroupRepository;
import md.sotea.journal_as_a_table.service.StudentService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    StudentGroupRepository studentStudentGroupRepository;;

    @Autowired
    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/filter")
    public String getAllByFullName(Model model, @RequestParam String q) {
        model.addAttribute("students", studentService.getAllBySearch(q));
        return "students";
    }

    @GetMapping
    public String all(Model model) {
        model.addAttribute("students", studentService.getAll());
        return "students";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> one(@PathVariable("id") Long id) {
        return new ResponseEntity<Student>(studentService.getOne(id), HttpStatus.OK);
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @GetMapping("/new")
    public String createForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        List<StudentGroup> studentGroupList = studentStudentGroupRepository.findAll();
        model.addAttribute("studentGroupList", studentGroupList);
        return "new_student";
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @PostMapping
    public String saveNew(@ModelAttribute("student") Student student) {
        if (student.getFullName() != (null) && student.getStudentGroup() != null) {
            studentService.saveNew(student);
            return "redirect:/api/students";
        }
        return "redirect:/api/students/new";
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getOne(id));
        List<StudentGroup> studentGroupList = studentStudentGroupRepository.findAll();
        model.addAttribute("studentGroupList", studentGroupList);
        return "edit_student";
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @PostMapping("/{id}")
    public String update(
            @PathVariable("id") Long id,
            @ModelAttribute("student") Student student,
            Model model) {
        Student targetedStudent = studentService.getOne(id);
        targetedStudent.setFirstName(student.getFirstName());
        targetedStudent.setLastName(student.getLastName());
        targetedStudent.setStudentGroup(student.getStudentGroup());
        studentService.update(targetedStudent, id);
        return "redirect:/api/students";
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        studentService.delete(id);
        return "redirect:/api/students";
    }
}
