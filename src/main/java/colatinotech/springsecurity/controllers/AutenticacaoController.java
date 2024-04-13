package colatinotech.springsecurity.controllers;

import colatinotech.springsecurity.dtos.AuthDto;
import colatinotech.springsecurity.services.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String auth(@RequestBody AuthDto dto){

        var usuarioAuthenticationToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        manager.authenticate(usuarioAuthenticationToken);

        return autenticacaoService.obterToken(dto);
    }

}
