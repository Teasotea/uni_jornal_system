package md.sotea.journal_as_a_table.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import md.sotea.journal_as_a_table.entities.StudentGroup;
import md.sotea.journal_as_a_table.service.StudentGroupService;

import javax.annotation.security.RolesAllowed;

@Controller
@RequestMapping("/api/student_groups")
public class StudentGroupController {

    StudentGroupService studentGroupService;

    public StudentGroupController(StudentGroupService studentGroupService) {
        this.studentGroupService = studentGroupService;
    }

    @GetMapping("/filter")
    public String getAllByGroupName(Model model, @RequestParam String q) {
        model.addAttribute("student_group", studentGroupService.getAllBySearch(q));
        return "student_groups";
    }

    @GetMapping
    public String all(Model model) {
        model.addAttribute("student_group", studentGroupService.getAll());
        return "student_groups";
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentGroup> one(@PathVariable("id") Long id) {
        return new ResponseEntity<StudentGroup>(studentGroupService.getOne(id), HttpStatus.OK);
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @GetMapping("/new")
    public String createForm(Model model) {
        StudentGroup studentGroup = new StudentGroup();
        model.addAttribute("student_group", studentGroup);
        return "new_student_group";
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @PostMapping
    public String saveNew(@ModelAttribute("student_group") StudentGroup studentGroup) {
        studentGroupService.saveNew(studentGroup);
        return "redirect:/api/student_groups";
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("student_group", studentGroupService.getOne(id));
        return "edit_student_group";
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @PostMapping("/{id}")
    public String update(
            @PathVariable("id") Long id,
            @ModelAttribute("student_group") StudentGroup studentGroup,
            Model model) {
        StudentGroup targetedStudentGroup = studentGroupService.getOne(id);
        targetedStudentGroup.setGroupName(studentGroup.getGroupName());
        studentGroupService.update(targetedStudentGroup, id);
        return "redirect:/api/student_groups";
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        studentGroupService.delete(id);
        return "redirect:/api/student_groups";
    }
}
