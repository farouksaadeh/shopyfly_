package ch.shopyfly.shopyfly.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import org.springframework.core.io.ClassPathResource;
import java.nio.file.Files;
import java.nio.file.Path;

//WICHTIG! "name" in SQL Datenbank ist hier email!
//WICHTIG! "email" in SQL Datenbank ist hier password!
@Controller
@RequestMapping(path="/demo")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email, @RequestParam String fullName) {
        User n = new User();
        n.setName(name);
        n.setEmail(email);
        n.setFullname(fullName); // Setzen Sie den vollständigen Namen
        userRepository.save(n);
        return "Saved";
    }


    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping(path="/register")
    public @ResponseBody String registerUser (@RequestParam String name, @RequestParam String email, @RequestParam String fullName) {
        if(userRepository.findByName(name).isPresent()){
            return "Email bereits vorhanden.";
        }
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setFullname(fullName);
        userRepository.save(newUser);
        return "Benutzer erfolgreich registriert.";
    }

    @GetMapping(path="/register")
    @ResponseBody
    public String showRegistrationForm() {
        try {
            Path path = new ClassPathResource("static/registrationForm.html").getFile().toPath();
            return Files.readString(path);
        } catch (Exception e) {
            e.printStackTrace();
            return "Fehler beim Laden der HTML-Datei";
        }
    }

    @GetMapping(path="/findByName")
    public @ResponseBody String findByName(@RequestParam String name) {
        Optional<User> optionalUser = userRepository.findByName(name);
        if (optionalUser.isPresent()) {
            return optionalUser.get().getName() + ", " + optionalUser.get().getEmail();
        } else {
            return "User not found!";
        }
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
    public @ResponseBody String loginUser (@RequestParam String name, @RequestParam String email) {
        // Suche nach Benutzer in der Datenbank anhand des bereitgestellten Namens
        Optional<User> optionalUser = userRepository.findByName(name);

        // Wenn der Benutzer gefunden wurde
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // Überprüfen Sie die bereitgestellte E-Mail mit der in der Datenbank gespeicherten E-Mail
            if (email.equals(user.getEmail())) {
                return "Login erfolgreich ausgeführt";
            }
        }

        return "Benutzername oder E-Mail ist falsch!";
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
