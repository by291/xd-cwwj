package fun.by291.xdcwwj.controller;

import fun.by291.xdcwwj.base.BaseResponse;
import fun.by291.xdcwwj.base.ResponseCode;
import fun.by291.xdcwwj.exception.RegisterException;
import fun.by291.xdcwwj.model.converter.UserConverter;
import fun.by291.xdcwwj.model.entity.User;
import fun.by291.xdcwwj.model.form.IdsForm;
import fun.by291.xdcwwj.model.form.UserForm;
import fun.by291.xdcwwj.service.UserService;
import fun.by291.xdcwwj.util.ResponseUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bystander
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private UserConverter converter;

    /**
     * 注册用户
     *
     * @param userForm 用户信息
     */
    @PostMapping("/register")
    public BaseResponse<String> register(@RequestBody @Validated UserForm userForm) {
        try {
            User user = converter.form2Entity(userForm);
            service.register(user);
            return ResponseUtil.success(null);
        } catch (RegisterException e) {
            return ResponseUtil.fail(ResponseCode.CODE_BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * 获取用户信息（不包含密码）
     */
    @GetMapping
    public BaseResponse<List<User>> getAllUser() {
        return ResponseUtil.success(service.getUsersWithoutPassword());
    }

    @PatchMapping("/{id}")
    public BaseResponse<String> updateUser(@PathVariable int id, @RequestBody User user) {
        if (user.getEmail() != null && !user.getEmail().matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")) {
            return ResponseUtil.fail(ResponseCode.CODE_BAD_REQUEST, "invalid params");
        }
        service.updateUser(id, user);
        return ResponseUtil.success(null);
    }

    @PatchMapping("/disable")
    public BaseResponse<String> disableUsers(@RequestBody IdsForm idsForm) {
        service.updateUserStatus(idsForm.getIds(), 0);
        return ResponseUtil.success(null);
    }

    @PatchMapping("/enable")
    public BaseResponse<String> enableUsers(@RequestBody IdsForm idsForm) {
        service.updateUserStatus(idsForm.getIds(), 1);
        return ResponseUtil.success(null);
    }

    @PatchMapping("/email/disable")
    public BaseResponse<String> disableEmails(@RequestBody IdsForm idsForm) {
        service.updateUserStatus(idsForm.getIds(), 0);
        return ResponseUtil.success(null);
    }

    @PatchMapping("/email/enable")
    public BaseResponse<String> enableEmails(@RequestBody IdsForm idsForm) {
        service.updateUserStatus(idsForm.getIds(), 1);
        return ResponseUtil.success(null);
    }
}
