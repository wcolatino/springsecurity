package colatinotech.springsecurity.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum Role {
    ADMIN("admin"),
    USER("user");

    private String role;

    Role(String role) {
        this.role = role;
    }
}
