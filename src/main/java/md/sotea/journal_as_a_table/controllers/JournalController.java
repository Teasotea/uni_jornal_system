package md.sotea.journal_as_a_table.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import md.sotea.journal_as_a_table.entities.Journal;
import md.sotea.journal_as_a_table.service.JournalService;

import javax.annotation.security.RolesAllowed;

@Controller
@RequestMapping("/api/journal")
public class JournalController {

    JournalService journalService;

    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @GetMapping("/filter")
    public String getAllByFullName(Model model, @RequestParam String q) {
        model.addAttribute("journal", journalService.getAllBySearch(q));
        return "journal";
    }

    @GetMapping
    public String all(Model model) {
        model.addAttribute("journal", journalService.getAll());
        return "journal";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Journal> one(@PathVariable("id") Long id) {
        return new ResponseEntity<Journal>(journalService.getOne(id), HttpStatus.OK);
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @GetMapping("/new")
    public String createForm(Model model) {
        Journal journal = new Journal();
        model.addAttribute("journal", journal);
        return "new_journal";
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @PostMapping
    public String saveNew(@ModelAttribute("journal") Journal journal) {
        journalService.saveNew(journal);
        return "redirect:/api/journal";
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("journal", journalService.getOne(id));
        return "edit_journal";
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @PostMapping("/{id}")
    public String update(
            @PathVariable("id") Long id,
            @ModelAttribute("journal") Journal journal,
            Model model) {
        Journal targetedJournal = journalService.getOne(id);
        // targetedJournal.setName(journal.getName());
        // targetedJournal.setStudentGroup(journal.getStudentGroup());
        targetedJournal.setWeek(journal.getWeek());
        targetedJournal.setTotal(journal.getTotal());
        targetedJournal.setP1(journal.getP1());
        targetedJournal.setFinal_grade(journal.getFinal_grade());
        targetedJournal.setFinal_mark(journal.getFinal_mark());
        journalService.update(targetedJournal, id);
        return "redirect:/api/journal";
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        journalService.delete(id);
        return "redirect:/api/journal";
    }

}
