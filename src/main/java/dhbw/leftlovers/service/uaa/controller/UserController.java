package dhbw.leftlovers.service.uaa.controller;

import dhbw.leftlovers.service.uaa.entity.User;
import dhbw.leftlovers.service.uaa.entity.UserResponse;
import dhbw.leftlovers.service.uaa.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@CrossOrigin
@RestController
@RequestMapping("/UAAService")
public class UserController {

    @Autowired
    private UserService userService;
/*

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserService userService,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
*/

    @Autowired
    private ModelMapper modelMapper;

    // TODO: Spring Boot Actuator einbinden

    @GetMapping(path = "/wakeup", produces = "application/json")
    @ResponseBody String wakeUp() {
        return "\"I'm already up!\"";
    }

    @GetMapping("/validate")
    public boolean validateToken(HttpServletRequest req) {
        return userService.validateToken(req);
    }

 /*   @PostMapping("/signup")
    ResponseEntity<?> signUp(@RequestBody User user) {
        this.checkIfUsernameIsPresent(user.getUsername());
        this.checkIfEmailIsPresent(user.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User response = userService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.getUserid()).toUri();


        return ResponseEntity.created(location).build();
    }*/

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        return userService.signup(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.login(user.getUsername(), user.getPassword());
    }

    @DeleteMapping("/{username}")
    public String delete(@PathVariable String username) {
        userService.delete(username);
        return username;

        // TODO: Rückgabewert ändern.
    }

    @GetMapping("/me")
    public UserResponse whoami(HttpServletRequest req) {
        return modelMapper.map(userService.whoami(req), UserResponse.class);
    }

/*    @PostMapping("/signup")
    HttpHeaders signUp(@RequestBody User user) {
        this.checkIfUsernameIsPresent(user.getUsername());
        this.checkIfEmailIsPresent(user.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User response = userService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.getUserid()).toUri();

        return userService.doLogin(user);
//        return ResponseEntity.created(location).build();
    }*/

/*    private void checkIfUsernameIsPresent(String username) {
        this.userService.findByUsername(username).ifPresent(user -> {
            throw new UsernameTakenException(username);
        });
    }

    private void checkIfEmailIsPresent(String email) {
        this.userService.findByEmail(email).ifPresent(user -> {
            throw new EmailTakenException(email);
        });
    }*/

    // TODO: Auto-Login
    // TODO: Token-Methode - Sicherheit / bessere Alternative
    // TODO: wie funktioniert Principal?
}
