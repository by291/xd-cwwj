package fun.by291.xdcwwj.component;

import fun.by291.xdcwwj.base.BaseResponse;
import fun.by291.xdcwwj.base.ResponseCode;
import fun.by291.xdcwwj.util.ResponseUtil;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<String> handleInvalidParam() {
        return ResponseUtil.fail(ResponseCode.CODE_BAD_REQUEST, "参数错误");
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse<String> handleException(Exception e) {
        return ResponseUtil.fail(500, e.getMessage());
    }
}
