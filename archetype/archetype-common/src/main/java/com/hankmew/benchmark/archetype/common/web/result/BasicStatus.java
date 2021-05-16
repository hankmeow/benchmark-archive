package com.hankmew.benchmark.archetype.common.web.result;

public enum BasicStatus implements Result.Status{
    system_error(-1),
    ok(0),
    param_invalid(1),
    ;
    private int code;

    BasicStatus(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }
}
