package dhbw.leftlovers.service.uaa.service;

import dhbw.leftlovers.service.uaa.entity.User;
import dhbw.leftlovers.service.uaa.exception.EmailTakenException;
import dhbw.leftlovers.service.uaa.exception.JWTValidationException;
import dhbw.leftlovers.service.uaa.exception.LoginException;
import dhbw.leftlovers.service.uaa.exception.UsernameTakenException;
import dhbw.leftlovers.service.uaa.repository.UserRepository;
import dhbw.leftlovers.service.uaa.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    private static final String LOGIN_URL = "https://leftloversgateway.azurewebsites.net/UAAService/login";

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public String login(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username);
        } catch (AuthenticationException e) {
            throw new LoginException();
        }
    }

    @Override
    public String signup(User user) {
        this.checkIfUsernameIsPresent(user.getUsername());
        this.checkIfEmailIsPresent(user.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        this.save(user);
        return jwtTokenProvider.createToken(user.getUsername());
    }

    @Override
    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    public User whoami(HttpServletRequest req) {
        return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req))).orElseThrow(JWTValidationException::new);
    }

    @Override
    public boolean validateToken(HttpServletRequest req) {
        return jwtTokenProvider.validateToken(req);
    }



   /* public HttpHeaders doLogin(User user) {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = URI.create(LOGIN_URL);
//        RequestEntity<?> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
        HttpEntity<User> request = new HttpEntity<>(user);
        ResponseEntity<User> response = restTemplate.exchange(uri, HttpMethod.POST, request, User.class);
        return response.getHeaders();
    }*/

    private void checkIfUsernameIsPresent(String username) {
        this.findByUsername(username).ifPresent(user -> {
            throw new UsernameTakenException(username);
        });
    }

    private void checkIfEmailIsPresent(String email) {
        this.findByEmail(email).ifPresent(user -> {
            throw new EmailTakenException(email);
        });
    }
}
