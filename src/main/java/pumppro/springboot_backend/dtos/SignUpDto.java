package pumppro.springboot_backend.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignUpDto {
    @NotEmpty
    private String email;

    @NotEmpty
    private String login;

    @NotEmpty
    private char[] password;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

}
