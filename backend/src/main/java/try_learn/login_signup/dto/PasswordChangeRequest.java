package try_learn.login_signup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // generates getters setters toString
@NoArgsConstructor // required for JSON deserialization
@AllArgsConstructor // useful for creating the object in tests
public class PasswordChangeRequest {
    private String currentPassword;
    private String newPassword;
}
