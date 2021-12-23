package fun.by291.xdcwwj.model.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Email
    private String email;

    private Integer enableUser;

    private Integer enableEmail;
}
