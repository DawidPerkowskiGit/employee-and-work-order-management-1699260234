package dpapps.security.userregistration;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * User registration template data
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotEmpty(message = "Login should not be empty")
    private String login;
    @NotEmpty(message = "Password should not be empty")
    private String password;
    @NotEmpty(message = "Your name should not be empty")
    private String name;
    @NotEmpty(message = "Email should not be empty")
    private String email;


}