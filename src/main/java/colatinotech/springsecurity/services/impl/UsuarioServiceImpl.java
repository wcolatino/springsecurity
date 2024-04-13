package colatinotech.springsecurity.services.impl;

import colatinotech.springsecurity.dtos.UsuarioDto;
import colatinotech.springsecurity.models.Usuario;
import colatinotech.springsecurity.repositories.UsuarioRepository;
import colatinotech.springsecurity.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioRepository repository;


    @Override
    public UsuarioDto salvar(UsuarioDto dto) {

        Usuario usuarioJaExistente = repository.findByLogin(dto.login());

        if (usuarioJaExistente!=null){
            throw new RuntimeException("Usuário Já existente!");
        }

        var passwordHash = encoder.encode(dto.senha());

        Usuario usuario = new Usuario(dto.nome(), dto.login(), passwordHash);
        Usuario usuarioSalvo = repository.save(usuario);
        return new UsuarioDto(usuarioSalvo.getNome(), usuarioSalvo.getLogin(), usuarioSalvo.getSenha());
    }
}
