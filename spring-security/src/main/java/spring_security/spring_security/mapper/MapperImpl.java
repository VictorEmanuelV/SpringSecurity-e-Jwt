package spring_security.spring_security.mapper;

import org.springframework.stereotype.Component;
import spring_security.spring_security.dto.UsuarioDto;
import spring_security.spring_security.entity.Usuario;
import spring_security.spring_security.interfaces.Mapper;

@Component
public class MapperImpl implements Mapper {
    @Override
    public Usuario toUsuario(UsuarioDto usuarioDto) {
        return new Usuario(
                usuarioDto.getNome(),
                usuarioDto.getLogin(),
                usuarioDto.getSenha(),
                usuarioDto.getRole());
    }
    @Override
    public UsuarioDto toDto(Usuario usuario) {
        return new UsuarioDto(
                usuario.getNome(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getRole());
    }
}
