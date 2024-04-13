package colatinotech.springsecurity.controllers;

import colatinotech.springsecurity.dtos.UsuarioDto;
import colatinotech.springsecurity.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;


    @PostMapping
    public UsuarioDto salvar (@RequestBody UsuarioDto dto){
        return service.salvar(dto);
    }

}
