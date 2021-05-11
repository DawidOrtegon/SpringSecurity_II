package dfob.data.springsecurity_ii;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtFilter implements javax.servlet.Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String header = httpServletRequest.getHeader("Authorization");

        // Verify if it is not null or if does not begin with the word bearer (Portador, si es igual a null o no inicia con el string dado, de error).
        if(httpServletRequest == null || !header.startsWith("Bearer "))
        {
            throw new ServletException("Wrong or empty header");
        }
        else
        {
            try
            {
                // Se toma el token, se parsea, se le pasa la clave con el cual fue generado y se obtiene todo.
                String token =  header.substring(7);
                Claims claims  = Jwts.parser().setSigningKey("byku123").parseClaimsJws(token).getBody();
                servletRequest.setAttribute("claims", claims);
            }

            catch (Exception e)
            {
                throw new ServletException("Wrong key");
            }

        }

        filterChain.doFilter(servletRequest, servletResponse);

    }
}
