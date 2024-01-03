package md.sotea.journal_as_a_table;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        // Your login validation logic here

        // If login fails, set an error message
        model.addAttribute("error", "Invalid username or password");

        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}