package md.sotea.journal_as_a_table.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import md.sotea.journal_as_a_table.entities.Course;
import md.sotea.journal_as_a_table.entities.Teacher;
import md.sotea.journal_as_a_table.repositories.TeacherRepository;
import md.sotea.journal_as_a_table.service.CourseService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    TeacherRepository courseTeacherRepository;

    @Autowired
    CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/filter")
    public String getAllByName(Model model, @RequestParam String q) {
        model.addAttribute("courses", courseService.getAllBySearch(q));
        return "courses";
    }

    @GetMapping
    public String all(Model model) {
        model.addAttribute("courses", courseService.getAll());
        return "courses";
    }

    @GetMapping("{id}")
    public ResponseEntity<Course> one(@PathVariable("id") Long id) {
        return new ResponseEntity<Course>(courseService.getOne(id), HttpStatus.OK);
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @GetMapping("/new")
    public String createForm(Model model) {
        Course course = new Course();
        model.addAttribute("course", course);
        List<Teacher> teacherList = courseTeacherRepository.findAll();
        model.addAttribute("teacherList", teacherList);
        return "new_course";
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @PostMapping
    public String saveNew(@ModelAttribute("course") Course course) {
        if (course.getName() != (null) && course.getTeacher() != null) {
            courseService.saveNew(course);
            return "redirect:/api/courses";
        }
        return "redirect:/api/courses/new";
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseService.getOne(id));
        List<Teacher> teacherList = courseTeacherRepository.findAll();
        model.addAttribute("teacherList", teacherList);
        return "edit_course";
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @PostMapping("/{id}")
    public String update(
            @PathVariable("id") Long id,
            @ModelAttribute("course") Course course,
            Model model) {
        Course targetedCourse = courseService.getOne(id);
        targetedCourse.setName(course.getName());
        targetedCourse.setTeacher(course.getTeacher());
        courseService.update(targetedCourse, id);
        return "redirect:/api/courses";
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        courseService.delete(id);
        return "redirect:/api/courses";
    }

}
