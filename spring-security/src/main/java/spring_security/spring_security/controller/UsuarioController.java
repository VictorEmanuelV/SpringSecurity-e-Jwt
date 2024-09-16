package spring_security.spring_security.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring_security.spring_security.dto.UsuarioDto;
import spring_security.spring_security.interfaces.UsuarioService;

@AllArgsConstructor
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private UsuarioService usuarioService;

    @PostMapping
    public UsuarioDto salvar(@RequestBody UsuarioDto usuarioDto){
        return usuarioService.salvar(usuarioDto);
    }
    @GetMapping("/admin")
    public String permissaoAdmin(){
        return "Permissao Administrador";
    }
    @GetMapping("/user")
    public String permissaoUser(){
        return "Permissao de usuario";
    }
}
