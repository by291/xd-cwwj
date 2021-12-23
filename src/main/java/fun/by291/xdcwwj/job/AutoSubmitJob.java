package fun.by291.xdcwwj.job;

import fun.by291.xdcwwj.base.BaseResult;
import fun.by291.xdcwwj.component.MailTemplate;
import fun.by291.xdcwwj.component.SubmitHttpTemplate;
import fun.by291.xdcwwj.config.MailProperties;
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
    private MailTemplate mailTemplate;
    @Autowired
    private UserMapper mapper;
    @Autowired
    private MailProperties properties;

    @Scheduled(cron = "0 0 8,13,19 * * *")
    public void scheduledSubmitTask() throws EmailException {
        List<User> users = mapper.getUsers();
        for (User user : users) {
            if (user.getEnableUser() == 0) continue;

            BaseResult result = submitHttp.login(user);
            if (!"0".equals(result.getE())) continue;
            result = submitHttp.submit();
            if (("0".equals(result.getE()) || "1".equals(result.getE()))) {
                if (user.getEmail() != null && user.getEnableEmail() == 1) {
                    Email email = mailTemplate.getBasicEmail();
                    email.addTo(user.getEmail());
                    email.setSubject(properties.getSubject());
                    email.setMsg(properties.getContent());
                    email.send();
                }
            }
        }
    }
}
