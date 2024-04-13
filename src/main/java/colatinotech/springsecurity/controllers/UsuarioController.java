package colatinotech.springsecurity.controllers;

import colatinotech.springsecurity.dtos.UsuarioDto;
import colatinotech.springsecurity.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;


    @PostMapping
    public UsuarioDto salvar (@RequestBody UsuarioDto dto){
        return service.salvar(dto);
    }

    @GetMapping
    public String retornaTexto(){
        return "Acesso concedido!";
    }

    @GetMapping("/admin")
    public String getAdmin(){
        return "Acesso de administrador concedido!";
    }

    @GetMapping("/user")
    public String getUser(){
        return "Acesso de usuário concedido!";
    }

}
