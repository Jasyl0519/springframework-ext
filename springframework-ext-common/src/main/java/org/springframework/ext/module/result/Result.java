package org.springframework.ext.module.result;

import lombok.Data;
import org.springframework.ext.common.exception.NestedRuntimeException;
import org.springframework.ext.common.helper.JsonHelper;
import org.springframework.ext.common.object.Status;

/**
 * 结果对象
 *
 * @param <T> 模板
 * @author only 2013-7-2
 */
@Data
public class Result<T> implements ResultSupport<T> {

    /** Define long serialVersionUID */
    private static final long serialVersionUID = 3928402875296079225L;
    /** 状态 */
    private boolean success = true;
    /** 状态码 例如:0-正常；-1-缺少参数；-3-接口异常 */
    private int status;
    /** 错误码 */
    private String code;
    /** 状态消息 */
    private String msg;
    /** 淘宝/tmall商品或店铺的返利信息;外网B2C商品或活动的返利信息 */
    private T data;

    /** 构造函数 */
    public Result() {
        super();
    }

    /**
     * 设置状态码
     *
     * @param status 状态码
     */
    public static Result create(Status status) {
        return create(status.isSuccess(), status.getStatus(), status.getCode(), status.getMsg());
    }

    /**
     * 根据异常设置状态码
     *
     * @param e 异常
     */
    public static Result create(NestedRuntimeException e) {
        return create(false, e.getStatus(), e.getCode(), e.getMsg());
    }

    /**
     * 设置状态码
     *
     * @param status 状态码
     */
    public static Result create(boolean success, int status, String errorCode, String message) {
        Result result = new Result();
        result.setSuccess(success);
        result.setStatus(status);
        result.setCode(errorCode);
        result.setMsg(message);

        return result;
    }

    /**
     * 设置状态码
     *
     * @param status   状态码
     * @param messages 替换文本
     */
    public static Result create(Status status, String... messages) {
        return create(status.isSuccess(), status.getStatus(), status.getCode(), status.getMsg(), messages);
    }

    /**
     * 设置状态码
     *
     * @param status   状态码
     * @param messages 替换文本
     */
    public static Result create(boolean success, int status, String errorCode, String message, String... messages) {
        Result result = new Result();

        result.setSuccess(success);
        result.setStatus(status);
        result.setCode(errorCode);
        if (message != null && message.contains("%s")) {
            if (messages != null) {
                message = String.format(message, (Object[]) messages);
            }
            // 无替换文本
            else {
                message = message.replaceAll("%s", "");
            }
        }
        result.setMsg(message);

        return result;
    }

    @Override
    public String toString() {
        return JsonHelper.toJson(this);
    }
}