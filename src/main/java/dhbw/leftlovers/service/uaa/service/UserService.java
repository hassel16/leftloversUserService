package dhbw.leftlovers.service.uaa.service;

import dhbw.leftlovers.service.uaa.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    User save(User user);

    String login(String username, String password);

    String signup(User user);

    void delete(String username);

    User whoami(HttpServletRequest req);

    boolean validateToken(HttpServletRequest req);

    String getIdFromJWT(String token);
}
