package spring_security.spring_security.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import spring_security.spring_security.entity.Usuario;
import spring_security.spring_security.interfaces.AutenticacaoService;
import spring_security.spring_security.repository.UsuarioRepository;
import java.io.IOException;

@AllArgsConstructor
@Component
public class SecurityFilter extends OncePerRequestFilter {
    private AutenticacaoService autenticacaoService;
    private UsuarioRepository usuarioRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = extrairTokenDoHeader(request);

        if(token != null){
            String login = autenticacaoService.validarToken(token);
            Usuario usuario = usuarioRepository.findByLogin(login);

            var authentication =
                    new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request,response);
    }
    public String extrairTokenDoHeader(HttpServletRequest request){
        String token = request.getHeader("Authorization");

        if(token == null){
            return null;
        }
        if(!token.split(" ")[0].equals("Bearer")){
            return null;
        }
        return token.split(" ")[1];
    }
}
