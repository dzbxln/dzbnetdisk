package com.dengzebin.netdisk.untils;

/**
 * @Author DengZebing
 * @Description TODO
 * @Date 2023/5/30 15:08
 */

//统一信息返回
public class Result {
    private Integer code;
    private String message;
    private Object data;

    public Result() {

    }

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result(Object data) {
        this.data = data;
    }

    //成功时返回
    public static Result success() {
        Result result = new Result();
        result.setCode(0);
        result.setMessage("成功");
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(0);
        result.setMessage("成功");
        result.setData(data);
        return result;
    }

    public static Result success(String message, Object data) {
        Result result = new Result();
        result.setCode(0);
        result.setMessage(message);
        result.setData(data);
        return result;
    }


    public static Result error(String message, Object data) {
        Result result = new Result();
        result.setCode(-1);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    //错误时返回
    public static Result error(Integer code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static Result error(Integer code, String message, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

}

