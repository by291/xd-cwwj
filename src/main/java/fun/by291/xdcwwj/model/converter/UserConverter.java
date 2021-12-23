package fun.by291.xdcwwj.model.converter;

import fun.by291.xdcwwj.model.entity.User;
import fun.by291.xdcwwj.model.form.UserForm;
import org.mapstruct.Mapper;

@Mapper
public interface UserConverter {

    User form2Entity(UserForm userForm);
}
