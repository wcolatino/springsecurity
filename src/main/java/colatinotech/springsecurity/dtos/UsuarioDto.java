package colatinotech.springsecurity.dtos;

import colatinotech.springsecurity.enums.Role;

public record UsuarioDto(
        String nome,
        String login,
        String senha,
        Role role
) {
}
