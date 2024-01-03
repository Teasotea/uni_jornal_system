
package md.sotea.journal_as_a_table.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import md.sotea.journal_as_a_table.security_mysql.models.User;
import md.sotea.journal_as_a_table.repositories.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public RedirectView register(@RequestParam String username, @RequestParam String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles("ROLE_ADMIN");
        user.setActive(true);

        userRepository.save(user);
        return new RedirectView("/login");
    }

    // @PostMapping("/login")
    // public RedirectView login(@RequestParam String username, @RequestParam String
    // password,
    // HttpServletRequest request) {
    // UsernamePasswordAuthenticationToken authReq = new
    // UsernamePasswordAuthenticationToken(username, password);
    // AuthenticationManager auth = auth.authenticate(authReq);
    // SecurityContext sc = SecurityContextHolder.getContext();
    // sc.setAuthentication(auth);
    // HttpSession session = request.getSession(true);
    // session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
    // sc);

    // // Set active to true for the logged in user
    // User user = UserRepository.findByUsername(username);
    // if (user != null) {
    // user.setActive(true);
    // userRepository.save(user);
    // }

    // return new RedirectView("/home");
    // }
}