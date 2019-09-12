package kz.samgau.webkafka.model.dto;

import kz.samgau.webkafka.model.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String password;
    private Set<RoleDto> roles;
}
