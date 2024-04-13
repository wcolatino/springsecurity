package colatinotech.springsecurity.services.impl;

import colatinotech.springsecurity.dtos.AuthDto;
import colatinotech.springsecurity.models.Usuario;
import colatinotech.springsecurity.repositories.UsuarioRepository;
import colatinotech.springsecurity.services.AutenticacaoService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class AuthenticacaoServiceImpl implements AutenticacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**/
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(login);
    }

    @Override
    public String obterToken(AuthDto dto) {
        Usuario usuario = usuarioRepository.findByLogin(dto.login());
        return gerarTokeJwt(usuario);
    }

    public String gerarTokeJwt(Usuario usuario){

        try {
            //Algoritmo da geração do Token
            Algorithm algorithm = Algorithm.HMAC256("my-secret"); //chave secret é como se fosse a chave do Token;

            return JWT.create()
                    .withIssuer("springsecurity") //nome da aplicação
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(gerarDataExpiracao())
                    .sign(algorithm);

        }catch (JWTCreationException e){
            e.getCause();
            throw new RuntimeException("Erro ao tentar gerar o token "+e.getMessage());
        }

    }

    private Instant gerarDataExpiracao() {
        return LocalDateTime.now() //Pega a hora atual
                .plusHours(8) //Adiciona 8 horas no token
                .toInstant(ZoneOffset.of("-03:00")); //Seta UTF de horário;
    }
}
