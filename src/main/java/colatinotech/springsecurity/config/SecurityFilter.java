package colatinotech.springsecurity.config;

import colatinotech.springsecurity.models.Usuario;
import colatinotech.springsecurity.repositories.UsuarioRepository;
import colatinotech.springsecurity.services.AutenticacaoService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter { //Toda requisição passará por aqui;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /*Método que filtrará as requisições*/
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = extrairTokenHeader(request);

        if (token != null){
            String login = autenticacaoService.validaTokenJwt(token);
            Usuario usuario = usuarioRepository.findByLogin(login);

            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication); //Método que validará perfis e autenticação
        }
        filterChain.doFilter(request,response); //Autorizou o requesto e o response

    }

    //Método para extrair o token do header
    public String extrairTokenHeader(HttpServletRequest request){

        var authHeader = request.getHeader("Authorization");

        if (authHeader == null){
            return null;
        }

        //Toke = Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzcHJpbmdzZWN1cml0eSIsInN1YiI6IndpbGxpZS5jb2xhdGlubyIsImV4cCI6MTcxMzAwMDc0NH0.4wn7HIVujl3mF3YzdpUWkr4yxPTMKhf8XFXBJBF-BRs
        if (!authHeader.split(" ")[0].equals("Bearer")){ //Se não tiver Bearer na posição 0 do token, retorna null;
                return null;
        }

        return authHeader.split(" ")[1]; //retorna o que está na posição 1 (Token = [0] + [1]);
    }
}
