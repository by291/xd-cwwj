package fun.by291.xdcwwj.component;

import fun.by291.xdcwwj.base.BaseResponse;
import fun.by291.xdcwwj.base.ResponseCode;
import fun.by291.xdcwwj.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<String> handleInvalidParam(MethodArgumentNotValidException e) {
        LOGGER.debug(e.getMessage());
        return ResponseUtil.fail(ResponseCode.CODE_BAD_REQUEST, "参数错误");
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse<String> handleException(Exception e) {
        LOGGER.debug(e.getMessage());
        return ResponseUtil.fail(ResponseCode.CODE_INTERNAL_ERROR, e.getMessage());
    }
}
