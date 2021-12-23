package fun.by291.xdcwwj.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class CookieManager implements CookieJar {
    private final Map<String, List<Cookie>> map = new ConcurrentHashMap<>();

    @NotNull
    @Override
    public List<Cookie> loadForRequest(@NotNull HttpUrl httpUrl) {
        return map.getOrDefault(httpUrl.host(), new ArrayList<>());
    }

    @Override
    public void saveFromResponse(@NotNull HttpUrl httpUrl, @NotNull List<Cookie> list) {
        map.put(httpUrl.host(), list);
    }
}
