package fun.by291.xdcwwj.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    private Integer id;

    private String username;

    private String password;

    private String email;

    private Integer enableUser;

    private Integer enableEmail;
}
