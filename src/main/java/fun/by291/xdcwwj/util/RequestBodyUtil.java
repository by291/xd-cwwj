package fun.by291.xdcwwj.util;

import java.lang.reflect.Field;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class RequestBodyUtil {

    public static <T> RequestBody model2RequestBody(T model) {
        FormBody.Builder builder = new FormBody.Builder();
        Field[] fields = model.getClass().getDeclaredFields();
        try {
            for (Field f : fields) {
                f.setAccessible(true);
                Object o = f.get(model);
                if (o != null) {
                    builder.add(f.getName(), o.toString());
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return builder.build();
    }
}
