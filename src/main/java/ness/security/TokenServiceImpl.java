package ness.security;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.util.*;
import java.util.logging.Logger;

@Service("theTokenService")
public class TokenServiceImpl implements TokenService {

    @Value("${token.secret}")
    private String secret;


    private UserDetailsService userService;

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    public TokenServiceImpl(@Qualifier("theUserDetailsService") UserDetailsService userDetailsService) {
        this.userService = userDetailsService;
    }

    @Override
    public String getToken(String username, String password) throws AuthenticationException {
        logger.info("Trying to get token for user: " + username);

        if (username == null || password == null) {
            logger.warning("Empty string");
            return null;
        }
        User user = (User) userService.loadUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {

            Map<String, Object> tokenData = new HashMap<>();

            tokenData.put("clientType", "user");
            tokenData.put("username", user.getUsername());
            tokenData.put("password", user.getPassword());
            tokenData.put("token_create_time", new Date().getTime());

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, 5);
            Date expDate = calendar.getTime();

            tokenData.put("token_expiration_time", calendar.getTime());
            JwtBuilder jwtBuilder = Jwts.builder();

            jwtBuilder.setExpiration(expDate);
            jwtBuilder.setClaims(tokenData);

            logger.info("Got token");
            return jwtBuilder.signWith(SignatureAlgorithm.HS512, secret).compact();
        } else {
            logger.warning("Authentication error");
            throw new AuthenticationException("Authentication error");
        }
    }

    @Override
    public User getUser(String token) {
        logger.info("Trying to get user by token");
        Map<String, Object> tokenData =
                (Map) Jwts.parser().setSigningKey(secret).parse(token).getBody();

        if (tokenData != null)
            return new User(
                    tokenData.get("username").toString(),
                    tokenData.get("password").toString(),
                    new ArrayList<>());
        return null;
    }
}
