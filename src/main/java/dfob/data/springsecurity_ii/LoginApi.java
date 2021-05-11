package dfob.data.springsecurity_ii;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class LoginApi
{
    // Se valida cuales son los usuarios de la base de datos y si tienen realmente el Token correcto.
    @PostMapping("/logIn")
    public String login(@RequestBody User user) {

        long currentTimeMillis = System.currentTimeMillis();
        // Return the token for the user, but it has to be made other class.
         return Jwts.builder()
                .setSubject(user.getLogin())
                .claim("roles","user")
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + 20000))
                .signWith(SignatureAlgorithm.HS512, user.getPassword())
                .compact();

    }
}
