package fun.by291.xdcwwj.util;

import fun.by291.xdcwwj.base.BaseResponse;

import static fun.by291.xdcwwj.base.ResponseCode.CODE_OK;

public class ResponseUtil {

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(CODE_OK, data, "操作成功");
    }

    public static <T> BaseResponse<T> fail(int code, String msg) {
        return new BaseResponse<>(code, null, msg);
    }
}
