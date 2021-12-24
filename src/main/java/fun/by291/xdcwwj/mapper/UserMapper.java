package fun.by291.xdcwwj.mapper;

import fun.by291.xdcwwj.model.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

@Mapper
public interface UserMapper {

    int addUser(User user) throws DataAccessException;

    List<User> getUsers();

    int countUserByUsername(String username);

    List<User> getUsersWithoutPassword();

    int updateUser(@Param("id") int id, @Param("user") User user);

    int updateUserStatus(@Param("ids") Integer[] ids, @Param("status") Integer status);

    int updateEmailStatus(@Param("ids") Integer[] ids, @Param("status") Integer status);
}
