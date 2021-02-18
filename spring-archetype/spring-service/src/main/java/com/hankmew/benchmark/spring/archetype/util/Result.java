package com.hankmew.benchmark.spring.archetype.util;

import java.util.Collection;

public class Result<T> {
    private final T data;
    private final Status status;

    /* 创建 */
    private Result(T data, Status status) {
        this.data = data;
        this.status = status;
    }
    public static<T> Result<T> success(T data) {
        return new Result<>(data, null);
    }
    public static<T> Result<T> failure(Status status) {
        return new Result<>(null, status);
    }
    public static<T> Result<T> failure() {
        return new Result<>(null, BasicStatus.FAILURE);
    }

    /* 状态 */
    public boolean isSuccess() {
        return this.status == null;
    }
    public boolean isFailure() {
        return this.status != null;
    }
    public boolean isFailureEquals(Status status) {
        if (this.status == null) {
            return false;
        }
        return this.status.equals(status);
    }
    public boolean isEmpty() {
        if (this.data == null) {
            return true;
        }else {
            if (this.data instanceof Collection) {
                return ((Collection<?>) this.data).isEmpty();
            }else {
                return false;
            }
        }
    }
    public boolean notEmpty() {
        return !isEmpty();
    }
    /* 获取 */
    public T get() {
        return this.data;
    }
    public T getDefault(T defaultData) {
        return isEmpty() ? defaultData : this.data;
    }

    public interface Status {

    }

    public enum BasicStatus implements Status {
        FAILURE, //未知系统异常，出现无法恢复的都使用这个状态即可
        ;
    }
}
