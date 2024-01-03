package md.sotea.journal_as_a_table.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import md.sotea.journal_as_a_table.entities.Activity;
import md.sotea.journal_as_a_table.service.ActivityService;
import md.sotea.journal_as_a_table.entities.Course;
import md.sotea.journal_as_a_table.repositories.CourseRepository;

import java.util.List;

import javax.annotation.security.RolesAllowed;

@Controller
@RequestMapping("/api/activities")
public class ActivityController {

    ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Autowired
    private CourseRepository activityCourseRepository;

    @GetMapping("/filter")
    public String getAllByName(Model model, @RequestParam String q) {
        model.addAttribute("activities", activityService.getAllBySearch(q));
        return "activities";
    }

    @GetMapping
    public String all(Model model) {
        model.addAttribute("activities", activityService.getAll());
        return "activities";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activity> one(@PathVariable("id") Long id) {
        return new ResponseEntity<Activity>(activityService.getOne(id), HttpStatus.OK);
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @GetMapping("/new")
    public String createForm(Model model) {
        Activity activity = new Activity();
        model.addAttribute("activity", activity);
        List<Course> courseList = activityCourseRepository.findAll();
        model.addAttribute("courseList", courseList);
        return "new_activity";
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @PostMapping
    public String saveNew(@ModelAttribute("activity") Activity activity) {
        activityService.saveNew(activity);
        return "redirect:/api/activities";
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("activity", activityService.getOne(id));
        List<Course> courseList = activityCourseRepository.findAll();
        model.addAttribute("courseList", courseList);
        return "edit_activity";
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @PostMapping("/{id}")
    public String update(
            @PathVariable("id") Long id,
            @ModelAttribute("activity") Activity activity,
            Model model) {
        Activity targetedActivity = activityService.getOne(id);
        targetedActivity.setName(activity.getName());
        targetedActivity.setDate(activity.getDate());
        targetedActivity.setCourse(activity.getCourse());
        activityService.update(targetedActivity, id);
        return "redirect:/api/activities";
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        activityService.delete(id);
        return "redirect:/api/activities";
    }
}
