package md.sotea.journal_as_a_table.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import md.sotea.journal_as_a_table.entities.Teacher;
import md.sotea.journal_as_a_table.service.TeacherService;

import javax.annotation.security.RolesAllowed;

@Controller
@RequestMapping("/api/teachers")
public class TeacherController {

    TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/filter")
    public String getAllByFullName(Model model, @RequestParam String q) {
        model.addAttribute("teachers", teacherService.getAllBySearch(q));
        return "teachers";
    }

    @GetMapping
    public String all(Model model) {
        model.addAttribute("teachers", teacherService.getAll());
        return "teachers";
    }

    @GetMapping("{id}")
    public ResponseEntity<Teacher> one(@PathVariable("id") Long id) {
        return new ResponseEntity<Teacher>(teacherService.getOne(id), HttpStatus.OK);
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @GetMapping("/new")
    public String createForm(Model model) {
        Teacher teacher = new Teacher();
        model.addAttribute("teacher", teacher);
        return "new_teacher";
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @PostMapping
    public String saveNew(@ModelAttribute("teacher") Teacher teacher) {
        teacherService.saveNew(teacher);
        return "redirect:/api/teachers";
    }
    // @CrossOrigin
    // @PostMapping
    // public ResponseEntity<Person> save(@RequestBody Teacher teacher) {
    // return new ResponseEntity<Teacher>(teacherService.saveNew(teacher),
    // HttpStatus.CREATED);
    // }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("teacher", teacherService.getOne(id));
        return "edit_teacher";
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @PostMapping("/{id}")
    public String update(
            @PathVariable("id") Long id,
            @ModelAttribute("teacher") Teacher teacher,
            Model model) {
        Teacher targetedTeacher = teacherService.getOne(id);
        targetedTeacher.setFirstName(teacher.getFirstName());
        targetedTeacher.setLastName(teacher.getLastName());
        teacherService.update(targetedTeacher, id);
        return "redirect:/api/teachers";
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        teacherService.delete(id);
        return "redirect:/api/teachers";
    }

}
