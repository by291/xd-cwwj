package fun.by291.xdcwwj.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "mail")
public class MailProperties {
    private String author;

    private String host;

    private String sender;

    private String password;

    private String subject;

    private String content;
}
