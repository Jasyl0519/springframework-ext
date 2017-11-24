package org.springframework.ext.module.result;

import java.io.Serializable;

/**
 * 返回结果接口
 *
 * @author only
 * @since 2016-04-28
 */
public interface ResultSupport<T> extends Serializable {
    boolean isSuccess();

    void setSuccess(boolean success);

    int getStatus();

    void setStatus(int status);

    String getCode();

    void setCode(String code);

    String getMsg();

    void setMsg(String msg);

    T getData();

    void setData(T data);
}