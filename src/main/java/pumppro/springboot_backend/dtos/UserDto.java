package pumppro.springboot_backend.dtos;

import lombok.*;
import pumppro.springboot_backend.enums.Role;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String login;
    private String email;
    private String firstName;
    private String lastName;
    private String token;
    private Role role;
} //UserDto
