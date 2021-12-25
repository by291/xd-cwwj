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
    private MailProperties properties;

    public Email getBasicMail(MailType type) throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName(properties.getHost());
        email.setSmtpPort(properties.getPort());
        email.setSSLOnConnect(true);
        email.setCharset("UTF-8");
        email.setAuthentication(properties.getSender(), properties.getPassword());
        email.setFrom(properties.getSender());
        email.setSubject(properties.getSubject());
        email.setMsg(getContentFromType(type));
        return email;
    }

    private String getContentFromType(MailType type) {
        if (type == MailType.SUCCESS) return properties.getSuccessContent();
        else if (type == MailType.FAIL) return properties.getFailContent();
        else if (type == MailType.ERROR_PASSWORD) return properties.getErrorPasswordContent();

        return null;
    }


    public enum MailType {
        SUCCESS,

        FAIL,

        ERROR_PASSWORD,
    }
}
