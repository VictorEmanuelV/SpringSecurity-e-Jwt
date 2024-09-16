package spring_security.spring_security.interfaces;

import spring_security.spring_security.dto.UsuarioDto;
import spring_security.spring_security.entity.Usuario;

public interface Mapper {

    public Usuario toUsuario(UsuarioDto usuarioDto);
    public UsuarioDto toDto(Usuario usuario);
}
