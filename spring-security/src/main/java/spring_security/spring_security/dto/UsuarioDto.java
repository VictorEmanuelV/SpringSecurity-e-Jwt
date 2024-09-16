package spring_security.spring_security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring_security.spring_security.enums.RoleEnum;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

    private String nome;
    private String login;
    private String senha;
    private RoleEnum role;
}
