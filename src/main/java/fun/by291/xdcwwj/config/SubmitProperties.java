package fun.by291.xdcwwj.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "submit")
public class SubmitProperties {
    private Integer sfzx;

    private Integer tw;

    private String area;

    private String city;

    private String province;

    private String address;

    private String geo_api_info;

    private Integer sfcyglq;

    private Integer sfyzz;

    private String qtqk;

    private Integer ymtys;
}

