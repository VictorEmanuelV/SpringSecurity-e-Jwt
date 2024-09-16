package spring_security.spring_security.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring_security.spring_security.dto.UsuarioDto;
import spring_security.spring_security.entity.Usuario;
import spring_security.spring_security.interfaces.Mapper;
import spring_security.spring_security.interfaces.UsuarioService;
import spring_security.spring_security.repository.UsuarioRepository;

@AllArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {
    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;
    private Mapper mapper;
    @Override
    public UsuarioDto salvar(UsuarioDto usuarioDto) {

        Usuario exist = usuarioRepository.findByLogin(usuarioDto.getLogin());

        if(exist != null){
            throw new RuntimeException("Usuario ja existe!");
        }

        usuarioDto.setSenha(passwordEncoder.encode(usuarioDto.getSenha()));
        Usuario entity = mapper.toUsuario(usuarioDto);

        Usuario user = usuarioRepository.save(entity);

        return mapper.toDto(user);
    }
}
