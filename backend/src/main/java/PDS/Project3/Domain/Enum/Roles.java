package PDS.Project3.Domain.Enum;

import PDS.Project3.Domain.Entities.Role;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Roles {

    ROLE_ADMIN("Administrator, all permissions",
            "READ:PERSON", "UPDATE:PERSON", "DELETE:PERSON", "CREATE:PERSON",
            "READ:ITEM", "UPDATE:ITEM", "DELETE:ITEM", "CREATE:ITEM"),
    ROLE_CLIENT("read and order",
            "READ:PERSON", "READ:ITEM"),
    ROLE_DONOR("read and donate",
            "READ:PERSON", "READ:ITEM"),
    ROLE_STAFF("read edit orders and inventory",
            "READ:PERSON", "READ:ITEM"),
    ROLE_VOLUNTEER("act and stuff",
            "READ:PERSON", "READ:ITEM");

    private final String description;
    private final List<String> authorities;

    Roles(String description, String... authorities) {
        this.description = description;
        this.authorities = Arrays.asList(authorities);
    }

    public static Roles fromString(String roleName) {
        return Arrays.stream(Roles.values())
                .filter(role -> role.name().equalsIgnoreCase(roleName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid role: " + roleName));
    }

}
