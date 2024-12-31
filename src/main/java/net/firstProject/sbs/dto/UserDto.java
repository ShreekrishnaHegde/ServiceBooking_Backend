package net.firstProject.sbs.dto;

import lombok.Data;
import net.firstProject.sbs.Enums.UserRole;

@Data
public class UserDto {
    private Long id;

    private String email;

    private String password;

    private String name;

    private String lastname;

    private String phone;

    private UserRole role;
}
