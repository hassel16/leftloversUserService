package dhbw.leftlovers.service.uaa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class LoginException extends RuntimeException {

    public LoginException() {
        super("Invalid username/password supplied.");
    }
}
