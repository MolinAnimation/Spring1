package med.voll.api.infra.security;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

@Service
public class TokenService {

    public String generarToken() {
        try {
            Algorithm algorithm = Algorithm.HMAC256("diegopass");
            return JWT.create()
                    .withIssuer("auth0").withSubject("diego rojas")
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException();
        }
    }
}
