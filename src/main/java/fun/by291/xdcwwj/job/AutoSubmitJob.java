package fun.by291.xdcwwj.job;

import fun.by291.xdcwwj.base.BaseResult;
import fun.by291.xdcwwj.component.MailTemplate;
import fun.by291.xdcwwj.component.SubmitHttpTemplate;
import fun.by291.xdcwwj.mapper.UserMapper;
import fun.by291.xdcwwj.model.entity.User;
import java.util.List;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AutoSubmitJob {
    @Autowired
    private SubmitHttpTemplate submitHttp;
    @Autowired
    private UserMapper mapper;
    @Autowired
    private MailTemplate mailTemplate;

    @Scheduled(cron = "0 0 8,13,19 * * *")
    public void scheduledSubmitTask() throws EmailException {
        List<User> users = mapper.getUsers();
        Email successEmail = mailTemplate.getBasicMail(MailTemplate.MailType.SUCCESS);
        Email failEmail = mailTemplate.getBasicMail(MailTemplate.MailType.FAIL);
        Email errorPasswordEmail = mailTemplate.getBasicMail(MailTemplate.MailType.ERROR_PASSWORD);

        for (User user : users) {
            if (user.getEnableUser() == 0) continue;
            // 用户邮件提醒是否开启
            boolean isEmailEnabled = user.getEmail() != null && user.getEnableEmail() == 1;

            BaseResult result = submitHttp.login(user);
            if (!"0".equals(result.getE())) {
                // 密码错误提醒
                if ("1".equals(result.getE()) && isEmailEnabled) {
                    errorPasswordEmail.addTo(user.getEmail());
                }
                continue;
            }
            result = submitHttp.submit();
            if (("0".equals(result.getE()) || "1".equals(result.getE()))) {
                if (isEmailEnabled) {
                    // 上报成功提醒
                    successEmail.addTo(user.getEmail());
                }
            } else {
                // 上报失败提醒
                if (isEmailEnabled) {
                    failEmail.addTo(user.getEmail());
                }
            }
        }
        if (!successEmail.getToAddresses().isEmpty()) successEmail.send();
        if (!failEmail.getToAddresses().isEmpty()) failEmail.send();
        if (!errorPasswordEmail.getToAddresses().isEmpty()) errorPasswordEmail.send();
    }
}
