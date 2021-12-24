package fun.by291.xdcwwj.service;

import fun.by291.xdcwwj.base.BaseResult;
import fun.by291.xdcwwj.component.SubmitHttpTemplate;
import fun.by291.xdcwwj.exception.RegisterException;
import fun.by291.xdcwwj.mapper.UserMapper;
import fun.by291.xdcwwj.model.entity.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * @author Bystander
 */
@Service
public class UserService {
    @Autowired
    private SubmitHttpTemplate submitHttpTemplate;
    @Autowired
    private UserMapper mapper;

    /**
     * 注册用户
     *
     * @param user 要注册的用户
     * @throws RegisterException   注册时校验账号密码错误，以及用户已经注册时会抛出的异常
     * @throws DataAccessException 可能会触发的 username 唯一索引异常
     */
    public void register(User user) throws RegisterException {
        // 判断用户是否已注册
        int count = mapper.countUserByUsername(user.getUsername());
        if (count == 1) throw new RegisterException("用户已注册，请勿重复注册");
        BaseResult result = submitHttpTemplate.login(user);
        if (!"0".equals(result.getE())) throw new RegisterException("用户名或密码错误");
        mapper.addUser(user);
    }

    /**
     * 返回不包含密码的用户信息
     *
     * @return 用户信息列表
     */
    public List<User> getUsersWithoutPassword() {
        return mapper.getUsersWithoutPassword();
    }

    /**
     * 更新用户信息
     *
     * @param id   要更新信息的用户的 id
     * @param user 要更新的用户信息
     */
    public void updateUser(int id, User user) {
        mapper.updateUser(id, user);
    }

    /**
     * 更新用户状态
     *
     * @param ids    要更新状态的用户的 id
     * @param status 更新后用户的状态，1 为启用，0 为禁用
     */
    public void updateUserStatus(Integer[] ids, Integer status) {
        mapper.updateEmailStatus(ids, status);
    }

    /**
     * 更新用户邮件通知状态
     *
     * @param ids    要更新状态的用户的 id
     * @param status 更新后用户邮件通知的状态，1 为启用，0 为禁用
     */
    public void updateEmailStatus(Integer[] ids, Integer status) {
        mapper.updateEmailStatus(ids, status);
    }
}
