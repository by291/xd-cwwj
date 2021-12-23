package fun.by291.xdcwwj.component;

import fun.by291.xdcwwj.config.MailProperties;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MailTemplate {
    @Autowired
    private MailProperties mailProperties;

    public Email getBasicEmail() throws EmailException {
        SimpleEmail email = new SimpleEmail();
        // 关闭 Debug 模式
        email.setDebug(false);
        // 开启 SSL 加密
        email.setSSLOnConnect(true);
        // 设置服务器地址
        email.setHostName(mailProperties.getHost());
        // 设置字符编码
        email.setCharset("UTF-8");
        // 设置发送者
        email.setFrom(mailProperties.getSender());
        // 设置发件验证
        email.setAuthentication(mailProperties.getSender(), mailProperties.getPassword());
        return email;
    }
}
