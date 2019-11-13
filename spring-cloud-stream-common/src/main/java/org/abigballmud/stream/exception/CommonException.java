package org.abigballmud.stream.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * description
 * </p>
 *
 * @author isacc 2019/11/12 16:20
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommonException extends RuntimeException {

    private final Integer status;
    private final String message;

    public CommonException(Integer status, String code, Object... parameters) {
        super(String.format(code, parameters));
        this.status = status;
        this.message = String.format(code, parameters);
    }

    public CommonException(String code, Object... parameters) {
        super(String.format(code, parameters));
        this.status = HttpStatus.OK.value();
        this.message = String.format(code, parameters);
    }

    public CommonException(Integer status, String code, Throwable cause) {
        super(code, cause);
        this.status = status;
        this.message = code;
    }

    public CommonException(String code, Throwable cause) {
        super(code, cause);
        this.status = HttpStatus.OK.value();
        this.message = code;
    }

}
