package fun.by291.xdcwwj.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import fun.by291.xdcwwj.base.BaseResult;
import fun.by291.xdcwwj.model.entity.User;
import java.io.IOException;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubmitHttpTemplate {
    private static final String LOGIN_URL = "https://xxcapp.xidian.edu.cn/uc/wap/login/check";
    private static final String SUBMIT_URL = "https://xxcapp.xidian.edu.cn/xisuncov/wap/open-report/save";

    @Autowired
    private OkHttpClient client;
    @Autowired
    private RequestBody submitRequestBody;

    public BaseResult login(User user) {
        FormBody form = new FormBody.Builder()
                .add("username", user.getUsername())
                .add("password", user.getPassword())
                .build();

        Request request = new Request.Builder()
                .url(LOGIN_URL)
                .post(form)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return new ObjectMapper().readValue(response.body().string(), BaseResult.class);
        } catch (IOException e) {
            return null;
        }
    }

    public BaseResult submit() {
        Request request = new Request.Builder()
                .url(SUBMIT_URL)
                .post(submitRequestBody)
                .header("Accept", "application/json, text/plain, */*")
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
                .header("Host", "xxcapp.xidian.edu.cn")
                .header("Origin", "https://xxcapp.xidian.edu.cn")
                .header("Referer", "https://xxcapp.xidian.edu.cn/ncov/wap/default/index")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36 Edg/87.0.664.66")
                .header("X-Requested-With", "XMLHttpRequest")
                .build();
        try (Response response = client.newCall(request).execute()) {
            return new ObjectMapper().readValue(response.body().string(), BaseResult.class);
        } catch (IOException e) {
            return null;
        }
    }
}
