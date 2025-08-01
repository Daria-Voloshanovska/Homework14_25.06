package ait.cohort5860.accounting.model;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "login")
@Document(collection = "users")
public class UserAccount {
    @Id
    private String login;
    @Setter
    private String password;
    @Setter
    private String firstName;
    @Setter
    private String lastName;

    @Singular
    private Set<Role> roles = new HashSet<>();

    public boolean addRole(String role) {
        return roles.add(Role.valueOf(role));
    }
    public boolean removeRole(String role) {
        return roles.remove(Role.valueOf(role.toUpperCase()));
    }
}
