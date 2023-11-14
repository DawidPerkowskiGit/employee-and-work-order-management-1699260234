package dpapps.security.changepassword;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDTO {
    public String login;
    public String email;

    public String password;
    public String repeatedPassword;
}
