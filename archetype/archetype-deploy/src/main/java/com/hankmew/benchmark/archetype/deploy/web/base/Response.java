package com.hankmew.benchmark.archetype.deploy.web.base;

import com.hankmew.benchmark.archetype.common.web.result.BasicStatus;
import com.hankmew.benchmark.archetype.common.web.result.Result;
import lombok.Data;

/**
 * 这里用个code,message,data这三种
 * 如果需要其他的可以再单独写返回值
 */
@Data
public class Response {
    private Integer code;
    private String message;
    private Object data;

    private Response(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Response make(Result<?> result) {
        int code;
        if (result.status() != null) {
            code = result.status().getCode();
        }else {
            if (result.isSuccess()) {
                code = BasicStatus.ok.getCode();
            }else {
                code = BasicStatus.system_error.getCode();
            }
        }
        return new Response(code, MessageSourceUtil.generateMessage(code), result.get());
    }

}
