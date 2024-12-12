package ru.itmentor.spring.boot_security.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmentor.spring.boot_security.demo.models.User;
import ru.itmentor.spring.boot_security.demo.models.Role;

import java.util.List;


@Data
@NoArgsConstructor
public class UserDTO {
    private int id;
    private String username;
    private String password;
    private List<String> roles;
    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.roles=user.getRoles().stream()
                .map(Role::getName)
                .toList();

    }
}
