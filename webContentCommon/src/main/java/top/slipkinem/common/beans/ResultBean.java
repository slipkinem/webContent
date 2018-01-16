package top.slipkinem.common.beans;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by slipkinem on 1/10/2018.
 */

@Data
public class ResultBean<T> implements Serializable {
    private static final long serialVersionId = 1L;

    public static final int NO_LOGIN = -1;

    public static final int SUCCESS = 0;

    public static final int CHECK_FAIL = 1;

    public static final int NO_PERMISSION = 2;

    public static final int LOGIN_ERROR = 3;

    public static final int UNKNOWN_EXCEPTION = -99;

    private String errorMessage = "success";

    private int errorCode = SUCCESS;

    private T data;

    public ResultBean() {
        super();
    }

    public ResultBean(T data) {
        super();
        this.data = data;
    }

    public ResultBean(Throwable e) {
        super();
        this.errorMessage = e.toString();
        this.errorCode = UNKNOWN_EXCEPTION;
    }
}
