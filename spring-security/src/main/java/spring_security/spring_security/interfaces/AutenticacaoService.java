package spring_security.spring_security.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;
import spring_security.spring_security.dto.AuthDto;
import spring_security.spring_security.entity.Usuario;

public interface AutenticacaoService extends UserDetailsService {

    public String validarToken(String token);
    public String pegarToken(AuthDto authDto);
}
