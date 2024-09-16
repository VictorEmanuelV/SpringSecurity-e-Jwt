package spring_security.spring_security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring_security.spring_security.dto.AuthDto;
import spring_security.spring_security.entity.Usuario;
import spring_security.spring_security.interfaces.AutenticacaoService;
import spring_security.spring_security.repository.UsuarioRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@AllArgsConstructor
@Service
public class AutenticacaoServiceImp implements AutenticacaoService {

    private UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(login);
    }

    @Override
    public String pegarToken(AuthDto authDto) {
        Usuario user = usuarioRepository.findByLogin(authDto.getLogin());
        return gerarToken(user);
    }

    public String gerarToken(Usuario usuario){

        try {
            Algorithm algorithm = Algorithm.HMAC256("secret-key");
            return JWT.create()
                    .withIssuer("auth-api")
                    .withExpiresAt(gerarDataExpiracao())
                    .withSubject(usuario.getLogin())
                    .sign(algorithm);
        }catch (JWTCreationException exception){
            throw new RuntimeException("Erro na geracao do token "+exception);
        }
    }
    @Override
    public String validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret-key");
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch(JWTVerificationException exception){
            throw new RuntimeException("Erro na validacao do token");
        }
    }
    private Instant gerarDataExpiracao() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }


}
