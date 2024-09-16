package spring_security.spring_security.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {
    ADMIN("admin"),
    USER("user");

    private String role;
}
