package fun.by291.xdcwwj;

import fun.by291.xdcwwj.component.MailTemplate;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MailTest {
    @Autowired
    private MailTemplate mailTemplate;

    @Test
    public void sendMail() throws EmailException {
        Email email = mailTemplate.getBasicMail(MailTemplate.MailType.SUCCESS);
        email.addTo("2823197499@qq.com");
        email.send();
    }
}
