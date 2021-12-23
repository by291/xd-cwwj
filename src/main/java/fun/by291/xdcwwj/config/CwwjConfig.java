package fun.by291.xdcwwj.config;

import fun.by291.xdcwwj.model.converter.UserConverter;
import fun.by291.xdcwwj.util.RequestBodyUtil;
import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CwwjConfig {

    @Bean
    @Autowired
    public OkHttpClient okHttpClient(CookieJar cookieJar) {
        return new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .build();
    }

    @Bean
    @Autowired
    public RequestBody submitRequestBody(SubmitProperties properties) {
        return RequestBodyUtil.model2RequestBody(properties);
    }

    @Bean
    public UserConverter userConverter() {
        return Mappers.getMapper(UserConverter.class);
    }
}
