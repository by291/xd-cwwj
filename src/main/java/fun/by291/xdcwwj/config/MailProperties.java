package fun.by291.xdcwwj.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "mail")
public class MailProperties {

    private String host;

    private int port;

    private String sender;

    private String password;

    private String subject;

    private String successContent;

    private String failContent;

    private String errorPasswordContent;
}
