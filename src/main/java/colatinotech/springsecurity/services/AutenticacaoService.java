package colatinotech.springsecurity.services;

import colatinotech.springsecurity.dtos.AuthDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AutenticacaoService extends UserDetailsService {

    public String obterToken(AuthDto dto);
}
