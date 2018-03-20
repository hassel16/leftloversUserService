package dhbw.leftlovers.service.uaa.controller;

import dhbw.leftlovers.service.uaa.entity.User;
import dhbw.leftlovers.service.uaa.exception.EmailTakenException;
import dhbw.leftlovers.service.uaa.exception.UsernameTakenException;
import dhbw.leftlovers.service.uaa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;

@RestController
@RequestMapping("/UAAService")
public class UserController {

    @Autowired
    private UserService userService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserService userService,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @GetMapping(path = "/wakeup", produces ="application/json")
    @ResponseBody String wakeUp(){
        return "\"I'm already up!\"";
    }

    @PostMapping("/token")
    ResponseEntity<?> user(Principal principal) {
        return ResponseEntity.ok(principal);
    }

    @CrossOrigin
    @PostMapping("/signup")
    ResponseEntity<?> signUp(@RequestBody User user) {
        this.checkIfUsernameIsPresent(user.getUsername());
        this.checkIfEmailIsPresent(user.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User response = userService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.getUserid()).toUri();

        return ResponseEntity.created(location).build();
    }

    private void checkIfUsernameIsPresent(String username) {
        this.userService.findByUsername(username).ifPresent(user -> {
            throw new UsernameTakenException(username);
        });
    }

    private void checkIfEmailIsPresent(String email) {
        this.userService.findByEmail(email).ifPresent(user -> {
            throw new EmailTakenException(email);
        });
    }

    // TODO: Auto-Login
    // TODO: Token-Methode - Sicherheit / bessere Alternative
    // TODO: wie funktioniert Principal?
}
