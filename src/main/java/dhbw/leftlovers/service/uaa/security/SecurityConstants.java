package dhbw.leftlovers.service.uaa.security;

public class SecurityConstants {

    public static final String SECRET = "SecretKeyToGenJWTs"; // TODO: read value from application.properties
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/UAAService/signup";
    public static final String LOG_IN_URL = "/UAAService/login";
    public static final String WAKE_UP_URL = "/UAAService/wakeup";
}
