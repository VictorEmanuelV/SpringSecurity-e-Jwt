package spring_security.spring_security.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring_security.spring_security.dto.AuthDto;
import spring_security.spring_security.interfaces.AutenticacaoService;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
    private AuthenticationManager authenticationManager;
    private AutenticacaoService autenticacaoService;
    @PostMapping
    public String auth(@RequestBody AuthDto authDto){

        var authentication =
                new UsernamePasswordAuthenticationToken(authDto.getLogin(),authDto.getSenha());
        authenticationManager.authenticate(authentication);

        return autenticacaoService.pegarToken(authDto);
    }
}
