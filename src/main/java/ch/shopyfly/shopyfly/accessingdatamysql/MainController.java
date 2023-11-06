package ch.shopyfly.shopyfly.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import org.springframework.core.io.ClassPathResource;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.ui.Model;

//WICHTIG! "name" in SQL Datenbank ist hier email!
//WICHTIG! "email" in SQL Datenbank ist hier password!
@Controller
@RequestMapping(path="/demo")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping(path="/register")
    public String registerUser (Model model, @ModelAttribute("user") User user) {

        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            model.addAttribute("emailError", "Email already exist");
            return "registrationForm";
        }
        userRepository.save(user);
        return "product-list";
    }

    @GetMapping(path="/register")
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registrationForm";
    }

    @GetMapping(path="/login")
    @ResponseBody
    public String showLoginForm() {
        try {
            Path path = new ClassPathResource("static/loginForm.html").getFile().toPath();
            return Files.readString(path);
        } catch (Exception e) {
            e.printStackTrace();
            return "Fehler beim Laden der HTML-Datei";
        }
    }

    @PostMapping(path="/login")
    public @ResponseBody String loginUser (@RequestParam String email, @RequestParam String password) {
        //TODO: add password validation

        // Suche nach Benutzer in der Datenbank anhand des bereitgestellten Namens
        Optional<User> optionalUser = userRepository.findByEmail(email);

        // Wenn der Benutzer gefunden wurde
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // Überprüfen Sie die bereitgestellte E-Mail mit der in der Datenbank gespeicherten E-Mail
            if (email.equals(user.getEmail())) {
                return "Login erfolgreich ausgeführt";
            }
        }

        return "E-Mail ist falsch!";
    }

    @PostMapping(path="/delete")
    public @ResponseBody String deleteUser(@RequestParam String emailToDelete) {
        Optional<User> optionalUser = userRepository.findByEmail(emailToDelete);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            return "Benutzer erfolgreich gelöscht.";
        } else {
            return "Benutzer mit dieser E-Mail-Adresse wurde nicht gefunden!";
        }
    }

    @GetMapping(path="/delete")
    @ResponseBody
    public String showDeleteForm() {
        try {
            Path path = new ClassPathResource("static/deleteUserForm.html").getFile().toPath();
            return Files.readString(path);
        } catch (Exception e) {
            e.printStackTrace();
            return "Fehler beim Laden der HTML-Datei";
        }
    }

    @PostMapping(path="/deleteAll")
    public @ResponseBody String deleteAllUsers() {
        userRepository.deleteAll();
        return "Alle Benutzer wurden gelöscht";
    }
}
