package io.github.Lisim2023.quotes.test.common;

import lombok.Data;

@Data
public class AjaxResult<T> {

    private boolean isQuote = false;

    private static final int SUCCESS_CODE = 200;
    private static final String SUCCESS_MESSAGE = "操作成功";

    private static final int FAIL_CODE = 400;
    private static final String FAIL_MESSAGE = "操作失败";

    private boolean success;
    private int code;
    private String message;
    private T data;



    public AjaxResult() {
    }

    public AjaxResult(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public AjaxResult(boolean success, int code, String message, T data) {
        this(success, code, message);
        this.data = data;
    }




    public static <T> AjaxResult<T> success() {
        return new AjaxResult<>(true, SUCCESS_CODE, SUCCESS_MESSAGE);
    }

    public static <T> AjaxResult<T> success(String message) {
        AjaxResult<T> r = success();
        r.setMessage(message);
        return r;
    }

    public static <T> AjaxResult<T> success(T data) {
        AjaxResult<T> r = success();
        r.setData(data);
        return r;
    }

    public static <T> AjaxResult<T> success(String message, T data) {
        AjaxResult<T> r = success();
        r.setMessage(message);
        r.setData(data);
        return r;
    }


    public static <T> AjaxResult<T> fail() {
        return new AjaxResult<>(false, FAIL_CODE, FAIL_MESSAGE);
    }

    public static <T> AjaxResult<T> fail(String message) {
        AjaxResult<T> r = fail();
        r.setMessage(message);
        return r;
    }




    public static <T> AjaxResult<T> quote(T data){
        AjaxResult<T> r = success(data);
        r.setQuote(true);
        return r;
    }

    public static <T> AjaxResult<T> quote(String message, T data){
        AjaxResult<T> r = success(message, data);
        r.setQuote(true);
        return r;
    }
}
