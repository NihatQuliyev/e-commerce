package delivery.management.system.model.enums;

import lombok.Getter;

@Getter
public enum SubjectType {
    REGISTRATION("Registration"),
    FORGET_PASSWORD("Forget password"),
    RENEW_PASSWORD("Renew password");

    private final String name;

    SubjectType(String name) {
        this.name = name;
    }
}
